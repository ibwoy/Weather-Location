package mai.uom.weather.location;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/**
 * Permission Manager Class
 * Check and Request permission for this app
 * Created by Panos on 15/1/2016.
 */
public class PermissionManager {
    public static final int PERMISSION_CODE_REQUEST_LOCATION = 10;

    /**
     * Check a permission if is granted to this package
     * MARSHMALLOW pre require
     * @param appContext Application Context
     * @param permission Permission for check
     * @return The result if permission is already granted returns true else false
     */
    public static boolean checkPermission(Context appContext,String permission) {
        return ContextCompat.checkSelfPermission(appContext, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Request a permission for the current package from an Activity or fragment
     * To manage the result from the request you must override the onRequestPermissionsResult
     * from your activity or fragment
     * @param activity The activity which request the permission
     * @param fragment if fragment is not null pass the request result to the fragment
     * @param permission The requested permission
     * @param permissionCode A code to associate the result
     * @return Returns true if the permission is already granted else false
     */
    public static boolean requestPermission(Activity activity,Fragment fragment,String permission,int permissionCode) {
        /* Request permission only if target sdk is equal or greater to marshmallow */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!checkPermission(activity,permission)) {
                if(fragment == null)
                    activity.requestPermissions(new String[]{permission}, permissionCode);
                else
                    fragment.requestPermissions(new String[]{permission}, permissionCode);
                return false;
            }
        }
        return true;
    }



}
