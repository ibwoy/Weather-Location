package mai.uom.weather.location;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;



/**
 * MapFragmentContainerClass
 * Represents the google map with all it's functionality
 */
public class MapFragmentContainer extends Fragment implements OnMapReadyCallback  {

    /** Google map object **/
    private GoogleMap map;
    /** Fragment view **/
    private View mapView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Resister the SupportMapFragment to pass
     * asynchronous callbacks to our MapContainerFragment
     */
    private void mapAsync() {
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Create the fragment view **/
        mapView = inflater.inflate(R.layout.fragment_map_container, container, false);
        /** Resister the callbacks **/
        mapAsync();
        return mapView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        /** Enable zoom buttons **/
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        /** Enable Marker options **/
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        enableMyLocation();

    }

    private void enableMyLocation() {
        if(PermissionManager.requestPermission(getActivity(),this,Manifest.permission.ACCESS_FINE_LOCATION,
                PermissionManager.PERMISSION_CODE_REQUEST_LOCATION)){
            map.setMyLocationEnabled(true);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("requestCode",Integer.toString(requestCode));
        switch (requestCode) {
            case PermissionManager.PERMISSION_CODE_REQUEST_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setMyLocationEnabled(true);
                }
                else
                    Toast.makeText(getActivity(),R.string.permission_denied,Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
