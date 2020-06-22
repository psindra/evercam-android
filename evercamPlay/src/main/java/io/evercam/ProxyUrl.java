package io.evercam;

import org.json.JSONException;
import org.json.JSONObject;

public class ProxyUrl extends EvercamObject {
    ProxyUrl(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getHls() throws JSONException {
        return jsonObject.getString("hls");
    }

    public String getRtmp() throws JSONException {
        return jsonObject.getString("rtmp");
    }
}