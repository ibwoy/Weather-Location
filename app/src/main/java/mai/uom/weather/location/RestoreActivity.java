package mai.uom.weather.location;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mai.uom.weather.location.datahelpers.DataWeatherHelper;

/**
 * Restore activity
 * Shows all the saved locations and restores or deletes them
 */
public class RestoreActivity extends AppCompatActivity {
    private ArrayList<WeatherResults> weatherLocations = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restore);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        loadData();
    }

    /**
     * Fill the list with the save data
     */
    private void loadData() {
        /** Retrieve data from the database **/
        final DataWeatherHelper weatherHelper = new DataWeatherHelper(this);
        weatherLocations = weatherHelper.findAll();
        /** Show then on list **/
        ListView listView = (ListView)findViewById(R.id.restoreList);
        RestoreListAdapter adapter = new RestoreListAdapter(this,R.layout.item_restore_list,weatherLocations);
        /** Handle the on delete event **/
        adapter.setDeleteCallback(new RestoreListAdapter.OnDeleteCallback() {
            @Override
            public void onDelete(int pos) {
                showConfirmDeleteDialog(pos);
            }
        });
        /** Handle on item select event **/
        listView.setAdapter(adapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /** Set result data **/
                Intent data = new Intent();
                data.putExtra("lat", weatherLocations.get(position).getLat());
                data.putExtra("lng", weatherLocations.get(position).getLon());
                data.putExtra("id", weatherLocations.get(position).getId());
                setResult(100, data);
                /** Finish the activity **/
                finish();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                /** Do nothing **/
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Shows a confirmation dialog before delete the location
     * @param pos position of the location in the array
     */
    private void showConfirmDeleteDialog(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete);
        builder.setMessage(R.string.confirm_delete_location);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataWeatherHelper weatherHelper = new DataWeatherHelper(RestoreActivity.this);
                /** Delete the selected weather location **/
                weatherHelper.delete(weatherLocations.get(pos).getId());
                /** Refresh the list **/
                loadData();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);

        builder.create().show();
    }

    /**
     * RestoreList adapter
     */
    public static class RestoreListAdapter extends BaseAdapter {
        private int resLayoutID;
        private Context context;
        ArrayList<WeatherResults> contents;
        private OnDeleteCallback deleteCallback;




        //Constructor
        public RestoreListAdapter(Context context, int layoutID, ArrayList<WeatherResults> contents) {

            this.context = context;
            this.contents = contents;
            resLayoutID = layoutID;

        }
        public void setDeleteCallback(OnDeleteCallback callback) {
            this.deleteCallback = callback;
        }
        public int getCount() {

            return contents.size();
        }

        public Object getItem(int position) {

            return contents.get(position);
        }

        public long getItemId(int position) {

            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            //Create item view
            ViewHolder holder;
            View row = convertView;

            if (row == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(resLayoutID, parent, false);


                holder = new ViewHolder();
                //Handle the objects of a list item
                holder.title = (TextView) row.findViewById(R.id.tvTitle);
                holder.imDelete = (ImageView) row.findViewById(R.id.imDelete);



                row.setTag(holder);
            } else {
                // Get the ViewHolder back to get fast access to the TextViews
                holder = (ViewHolder) row.getTag();
            }
            //Set values of objects for every item
            holder.title.setText(contents.get(position).getCity()+" "+contents.get(position).getCountry());
            holder.imDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(deleteCallback!=null) {
                        deleteCallback.onDelete(position);
                    }
                }
            });

            return row;


        }

        //Holds the values of every item
        static class ViewHolder {
            TextView title;
            ImageView imDelete;

        }
        public interface OnDeleteCallback {
            void onDelete(int pos);
        }
    }

}
