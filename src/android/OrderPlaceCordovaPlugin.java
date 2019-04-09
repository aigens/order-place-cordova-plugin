package com.aigens.sdk.plugin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.aigens.sdk.OrderPlace;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderPlaceCordovaPlugin extends CordovaPlugin {

    private static String TAG = "SdkOrderPlaceCordova";
    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;

        if (action.equals("openUrl")) {


            JSONObject params = args.optJSONObject(0);

            String url = params.getString("url");

            OrderPlace.openUrl(cordova.getActivity(), url, params);

        } else if (action.equals("scan")) {

            JSONObject params = args.optJSONObject(0);

            OrderPlace.scan(cordova.getActivity(), params);
        } else {

            //callbackContext.error("no method " + action);
            return false;
        }

        JSONObject result = new JSONObject();
        result.put("status", "success");

        Log.d(TAG, "execute:" + action);

        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, result);
        pluginResult.setKeepCallback(true);
        callbackContext.sendPluginResult(pluginResult);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (this.callbackContext != null) {
            if (resultCode == Activity.RESULT_OK) {
                JSONObject result = new JSONObject();

                switch (requestCode) {
                    case (OrderPlace.REQUEST_CODE_OPEN_URL):
                        try {
                            result.put("closeData", intent.getStringExtra("closeData"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, result);
                        pluginResult.setKeepCallback(true);
                        callbackContext.sendPluginResult(pluginResult);
                        break;
                    case (OrderPlace.REQUEST_CODE_SCAN):
                        try {
                            result.put("closeData", intent.getStringExtra("closeData"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK, result);
                        pluginResult2.setKeepCallback(true);
                        callbackContext.sendPluginResult(pluginResult2);
                        break;
                    default:
                        break;
                }
            } else {
                this.callbackContext.error("Unexpected error");
            }
        }

    }

    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
    }
}
