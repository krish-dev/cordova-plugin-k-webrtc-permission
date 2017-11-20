package in.co.indusnet.cordova.plugins.webrtc.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PermissionHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * KwebRTCPermission android
 * Created by Krishnendu Sekhar Das
 * Sr. Solution Architect, Indus Net Technologies Pvt. Ltd.
 */
public class KwebRTCPermission extends CordovaPlugin {

    private CallbackContext cordovaCallbackContext;

    private String OK_RESPONSE_CODE_KEY = "responseCode";
    private String OK_RESPONSE_MSG_KEY = "response";
    private String OK_RESPONSE_REQUIRED_APPROVED_KEY = "haveRequiredPermission";

    private int OK_CODE = 0;
    private int REQUEST_CODE = 1000;

    private String [] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.MODIFY_AUDIO_SETTINGS
    };

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        cordovaCallbackContext = callbackContext;

        if(action.equals("check")) {
            handleCheckPermission(permissions);
            return true;
        }else if(action.equals("request")) {
            handleRequestPermission(permissions);
            return true;
        }
        return false;
    }

    private void handleCheckPermission(String[] permissionArr) {
        JSONArray resultArr = new JSONArray();
        int permissionApproveCount = 0;
        try {
            for(String permission : permissionArr) {
                JSONObject obj = new JSONObject();
                obj.put("name", permission);
                obj.put("permission", checkPermission(permission));
                resultArr.put(obj);
                if(checkPermission(permission)) {
                    permissionApproveCount++;
                }
            }
            JSONObject message = new JSONObject();
            message.put(OK_RESPONSE_CODE_KEY, OK_CODE);
            message.put(OK_RESPONSE_MSG_KEY, resultArr);

            if(permissionApproveCount == 4) {
                message.put(OK_RESPONSE_REQUIRED_APPROVED_KEY, true);
            }else {
                message.put(OK_RESPONSE_REQUIRED_APPROVED_KEY, false);
            }
            cordovaCallbackContext.success(message);
        }catch (JSONException e) {
            cordovaCallbackContext.error(e.getMessage());
        }
    }

    private void handleRequestPermission(String[] permissionArr) {
        PermissionHelper.requestPermissions(this, REQUEST_CODE, permissionArr);
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        JSONArray resultArr = new JSONArray();

        try {
            int index = 0;
            int permissionApproveCount = 0;
            for(int r:grantResults)
            {
                JSONObject obj = new JSONObject();
                obj.put("name", permissions[index]);
                if(r == PackageManager.PERMISSION_GRANTED) {
                    obj.put("permission", true);
                    permissionApproveCount++;
                }else {
                    obj.put("permission", false);
                }
                resultArr.put(obj);
                index++;
            }

            JSONObject message = new JSONObject();
            message.put(OK_RESPONSE_CODE_KEY, OK_CODE);
            message.put(OK_RESPONSE_MSG_KEY, resultArr);
            if(permissionApproveCount == 4) {
                message.put(OK_RESPONSE_REQUIRED_APPROVED_KEY, true);
            }else {
                message.put(OK_RESPONSE_REQUIRED_APPROVED_KEY, false);
            }
            cordovaCallbackContext.success(message);
        }catch (JSONException e) {
            cordovaCallbackContext.error(e.getMessage());
        }

    }

    // Check permission use Cordova helper
    private boolean checkPermission(String permission) {
        return PermissionHelper.hasPermission(this, permission);
    }
}
