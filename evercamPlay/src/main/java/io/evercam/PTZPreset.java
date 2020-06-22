package io.evercam;

import io.evercam.unirest.http.HttpResponse;
import io.evercam.unirest.http.JsonNode;
import io.evercam.unirest.http.Unirest;
import io.evercam.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PTZPreset extends EvercamObject {
    public PTZPreset(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    private PTZPreset() {
    }

    public String getToken() {
        return getStringNotNull("token");
    }

    public String getName() {
        return getStringNotNull("Name");
    }

    /**
     * GET /cameras/{id}/ptz/presets
     *
     * @return a list contains all presets for the specified camera
     * @throws PTZException if any error occurs
     */
    public static ArrayList<PTZPreset> getAllPresets(String cameraId) throws PTZException {
        ArrayList<PTZPreset> presetsArrayList = new ArrayList<PTZPreset>();

        if (API.hasUserKeyPair()) {
            try {
                HttpResponse<JsonNode> response = Unirest.get(PTZPresetControl.getPresetsUrl(cameraId)).queryString(API.userKeyPairMap()).asJson();
                if (response.getStatus() == EvercamObject.CODE_OK) {
                    JSONArray presetsJsonArray = response.getBody().getObject().getJSONArray("Presets");
                    if (presetsJsonArray.length() > 0) {
                        for (int index = 0; index < presetsJsonArray.length(); index++) {
                            presetsArrayList.add(new PTZPreset(presetsJsonArray.getJSONObject(index)));
                        }
                    }
                } else {
                    ErrorResponse errorResponse = new ErrorResponse(response.getBody().getObject());
                    throw new PTZException(response.getStatus() + ": " + errorResponse.getMessage());
                }
            } catch (UnirestException e) {
                throw new PTZException(e);
            }catch (JSONException e) {
                throw new PTZException(e.getMessage());
            }
        } else {
            throw new PTZException(EvercamException.MSG_USER_API_KEY_REQUIRED);
        }

        return presetsArrayList;
    }

    /**
     * POST /cameras/{id}/ptz/presets/create/{preset_name}
     *
     * @param cameraId   the unique identifier of the camera to create preset on
     * @param presetName name of the new preset
     * @return token for the new created preset
     * @throws PTZException if any error occurred
     */
    public static String create(String cameraId, String presetName) throws PTZException {
        if (!API.hasUserKeyPair()) throw new PTZException(EvercamException.MSG_USER_API_KEY_REQUIRED);
        else {
            final String URL_CREATE = PTZPresetControl.getPresetsUrl(cameraId) + "/create";
            try {
                HttpResponse<JsonNode> response = Unirest.post(URL_CREATE).queryString(API.userKeyPairMap()).field("preset_name",presetName).asJson();
                if (response.getStatus() == CODE_CREATE || response.getStatus() == CODE_OK) {
                    return response.getBody().getObject().getString("PresetToken");
                } else {
                    ErrorResponse errorResponse = new ErrorResponse(response.getBody().getObject());
                    throw new PTZException(response.getStatus() + ": " + errorResponse.getMessage());
                }
            } catch (UnirestException e) {
                throw new PTZException(e);
            }catch (JSONException e) {
                throw new PTZException(e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return getToken() + ": " + getName();
    }
}
