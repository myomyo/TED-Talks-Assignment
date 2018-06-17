package com.mem.tedtalks.network.response;

import com.google.gson.annotations.SerializedName;
import com.mem.tedtalks.data.vos.TalkVO;

import java.util.List;

public class GetTalkResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("ted_talks")
    private List<TalkVO> tedTalks;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<TalkVO> getTedTalks() {
        return tedTalks;
    }

    public boolean isResponseOK() {
        return (code == 200 && tedTalks != null);
    }
}
