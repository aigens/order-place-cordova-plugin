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
        } else if (action.equals("scanDecode")) {

            JSONObject params = args.optJSONObject(0);

            OrderPlace.scanDecode(cordova.getActivity(), params);

        } else {

            //callbackContext.error("no method " + action);
            return false;
        }

        cordova.setActivityResultCallback(this);

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
        if (intent != null) {
            try {
                Log.d(TAG, "OrderPlaceCordovaPlugin onActivityResult: requestCode: " + requestCode + " resultCode: " + resultCode + "closeData: " + intent.getStringExtra("closeData"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);

        if (this.callbackContext != null) {
            if (resultCode == Activity.RESULT_OK) {
                JSONObject result = new JSONObject();

                switch (requestCode) {
                    case (10001):
                        try {
                            String jString = intent.getStringExtra("closeData");
                            JSONObject closeData = new JSONObject(jString);
                            result.put("closeData", closeData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, result);
                        pluginResult.setKeepCallback(true);
                        callbackContext.sendPluginResult(pluginResult);
                        break;
                    case (10002):
                        try {
                            String jString = intent.getStringExtra("closeData");
                            JSONObject closeData = new JSONObject(jString);
                            result.put("closeData", closeData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        PluginResult pluginResult2 = new PluginResult(PluginResult.Status.OK, result);
                        pluginResult2.setKeepCallback(true);
                        callbackContext.sendPluginResult(pluginResult2);
                        break;
                    case (10003):
                        try {
                            result.put("decodeResult", intent.getStringExtra("scanDecode"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        PluginResult pluginResult3 = new PluginResult(PluginResult.Status.OK, result);
                        pluginResult3.setKeepCallback(true);
                        callbackContext.sendPluginResult(pluginResult3);
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
