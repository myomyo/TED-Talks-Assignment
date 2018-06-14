package com.mem.tedtalks.data.vos;

import java.util.List;

public class TalkVO {

    private int talkId;
    private String title;
    private SpeakerVO speaker;
    private String imageUrl;
    private int durationInSec;
    private String description;
    private List<TagVO> tag;

    public int getTalkId() {
        return talkId;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDurationInSec() {
        return durationInSec;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTag() {
        return tag;
    }
}
