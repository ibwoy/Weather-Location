package mai.uom.weather.location;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
     * Resister the map fragment to receive
     * asynchronous callbacks from map object
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
        mapAsync();
        return mapView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }
}
