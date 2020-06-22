package io.evercam;

import io.evercam.unirest.http.HttpResponse;
import io.evercam.unirest.http.JsonNode;
import io.evercam.unirest.http.Unirest;
import io.evercam.unirest.http.exceptions.UnirestException;

public class PTZPresetControl implements PTZControl {
    private final String cameraId;
    private String moveToToken = "";

    public PTZPresetControl(String cameraId, String token) {
        this.cameraId = cameraId;
        this.moveToToken = token;
    }

    /**
     * POST /cameras/{id}/ptz/presets/go/{preset_token}
     *
     * @return true if the request is successful
     * @throws PTZException if any error occurred
     */
    @Override
    public boolean move() throws PTZException {
        String presetMoveUrl = getPresetsUrl(cameraId) + "/go/" + moveToToken;

        if (moveToToken.isEmpty()) throw new PTZException("Preset token needs to be specified");
        else if (!API.hasUserKeyPair()) throw new PTZException(EvercamException.MSG_USER_API_KEY_REQUIRED);
        else {
            try {
                HttpResponse<JsonNode> response = Unirest.post(presetMoveUrl).queryString(API.userKeyPairMap()).asJson();
                if (response.getStatus() == EvercamObject.CODE_CREATE || response.getStatus() == 200) {
                    return true;
                } else {
                    ErrorResponse errorResponse = new ErrorResponse(response.getBody().getObject());
                    throw new PTZException(response.getStatus() + ": " + errorResponse.getMessage());
                }
            } catch (UnirestException e) {
                throw new PTZException(e);
            }
        }
    }

    protected static String getPresetsUrl(String cameraId) {
        return URL + '/' + cameraId + "/ptz/presets";
    }
}
