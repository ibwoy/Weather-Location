package mai.uom.weather.location;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
                /** Delete the selected weather location **/
                weatherHelper.delete(weatherLocations.get(pos).getId());
                /** Refresh the list **/
                loadData();
            }
        });
        /** Handle on item select event **/
        listView.setAdapter(adapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
