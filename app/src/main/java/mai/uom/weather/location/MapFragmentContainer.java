package mai.uom.weather.location;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * MapFragmentContainerClass
 * Represents the google map with all it's functionality
 */
public class MapFragmentContainer extends Fragment implements OnMapReadyCallback  {

    /** Google map object **/
    private GoogleMap map;
    /** Fragment view **/
    private View mapView;
    /** Map container callbacks **/
    private MapContainerCallbacks callbacks;



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
        /** Resister the map fragment callbacks **/
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
        /** Enables my location **/
        enableMyLocation();
        /** Handles the event of long press on map **/
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                /** Push the location to the callbacks **/
                if (callbacks != null)
                    callbacks.onMarkerAdded(latLng.latitude, latLng.longitude);
                /** Add a maker to the location **/
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.draggable(false);
                map.addMarker(markerOptions);
                /** Vibrate to give feedback of the action to the user **/
                vibrate();
            }
        });

    }

    /**
     * Enable my location (Check for permission first)
     */
    private void enableMyLocation() {
        if(PermissionManager.requestPermission(getActivity(),this,Manifest.permission.ACCESS_FINE_LOCATION,
                PermissionManager.PERMISSION_CODE_REQUEST_LOCATION)){
            map.setMyLocationEnabled(true);
        }
    }

    /**
     * Vibrates the device for an instance
     */
    public void vibrate() {
        Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(25);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionManager.PERMISSION_CODE_REQUEST_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setMyLocationEnabled(true);
                }
                else //Toast a message to the user that permission denied(By him)
                    Toast.makeText(getActivity(),R.string.permission_denied,Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    /**
     * Set callbacks listener
     * @param callbacks The listener
     */
    public void setMapContainerCallbacks(MapContainerCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    /**
     * Map Container callbacks
     */
    public interface MapContainerCallbacks {
        void onMarkerAdded(double lat,double lng);
    }
}
