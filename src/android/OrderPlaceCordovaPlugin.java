package com.aigens.sdk.plugin;

import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class OrderPlaceCordovaPlugin extends CordovaPlugin {

    private static String TAG = "SdkOrderPlaceCordova";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        /*
        if (action.equals("echo")) {
            String message = args.getString(0);

            return true;
        }*/

        Log.d(TAG, "execute" + action);
        callbackContext.success("hello world!" + new Date());

        return true;
    }
}
