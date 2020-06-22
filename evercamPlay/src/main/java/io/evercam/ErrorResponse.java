package io.evercam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;

import java.util.ArrayList;

class ErrorResponse extends EvercamObject {
    ErrorResponse(JSONObject jsonObject) {

        this.jsonObject = jsonObject;
    }

    ErrorResponse(String jsonObjectString) {

        try {
            this.jsonObject = new JSONObject(jsonObjectString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected String getProperErrorMessage() throws EvercamException {
        if (!isMessageEmpty()) {
            return getMessage();
        } else {
            return getMessageFromContexts();
        }
    }

    /**
     * Return the message in error response.
     */
    protected String getMessage() {
        try {
            Iterator keyIterator = jsonObject.getJSONObject("message").keys();
            String key = null;
            while(keyIterator.hasNext()){
                key = (String)keyIterator.next();
            }
            String errorMessageString =  jsonObject.getJSONObject("message").getJSONArray(key).getString(0);
            return  errorMessageString;
//            return jsonObject.getString("message");
        } catch (JSONException e) {
            return "";
        }
    }

    protected boolean isMessageEmpty() {
        if (getMessage().equals("null") || getMessage().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Return the error context list in error response
     */
    protected ArrayList<String> getContexts() throws EvercamException {
        ArrayList<String> contextArray = new ArrayList<String>();
        try {
            JSONArray contextJsonArray = jsonObject.getJSONArray("context");
            if (contextJsonArray.length() != 0) {
                for (int index = 0; index < contextJsonArray.length(); index++) {
                    String context = contextJsonArray.getString(index);
                    contextArray.add(context);
                }
            }
        }catch (JSONException e) {
            throw new EvercamException(e);
        }
        return contextArray;
    }

    protected String getMessageFromContexts() throws EvercamException {
        String message = "";
        ArrayList<String> contextArray = getContexts();
        if (contextArray.size() != 0) {
            for (String context : contextArray) {
                String contextMessage = "Invalid " + context;
                message = message + "," + contextMessage;
            }
            return message;
        } else {
            throw new EvercamException(toString());
        }
    }
}
