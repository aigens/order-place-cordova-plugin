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


            String url = args.getString(0);
            JSONArray features = args.getJSONArray(1);

            OrderPlace.openUrl(cordova.getActivity(), url, toFeatures(features));
        }else if(action.equals("scan")){

            JSONArray features = args.getJSONArray(0);

            OrderPlace.scan(cordova.getActivity(), toFeatures(features));
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

    private List<String> toFeatures(JSONArray features){


        ArrayList<String> f = new ArrayList<>();

        if(features != null){
            for(int i = 0; i < features.length(); i++){
                String feature = features.optString(i, null);
                if(feature != null){
                    f.add(feature);
                }
            }
        }

        return f;
    }

}
