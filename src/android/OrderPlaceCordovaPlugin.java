package com.aigens.sdk.plugin;


import android.util.Log;

import com.aigens.sdk.OrderPlace;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderPlaceCordovaPlugin extends CordovaPlugin {

    private static String TAG = "SdkOrderPlaceCordova";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {



        if(action.equals("openUrl")){


            JSONObject params = args.optJSONObject(0);

            String url = params.getString("url");

            OrderPlace.openUrl(cordova.getActivity(), url, params);

        }else if(action.equals("scan")){

            JSONObject params = args.optJSONObject(0);

            OrderPlace.scan(cordova.getActivity(), params);
        }else{

            //callbackContext.error("no method " + action);
            return false;
        }

        JSONObject result = new JSONObject();
        result.put("status", "success");

        Log.d(TAG, "execute:" + action);
        callbackContext.success(result.toString());

        return true;
    }



}
