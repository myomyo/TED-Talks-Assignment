package com.mem.tedtalks.data.vos;

import com.google.gson.annotations.SerializedName;

public class SpeakerVO {

    @SerializedName("speaker_id")
    private int speakerId;

    @SerializedName("name")
    private String name;

    public int getSpeakerId() {
        return speakerId;
    }

    public String getName() {
        return name;
    }
}
