package com.mem.tedtalks.data.vos;

import java.util.List;

public class PlayListVO {

    private int playListId;
    private String title;
    private String imageUrl;
    private int totalTalks;
    private String description;
    private List<TalkVO> talksInPlayList;

    public int getPlayListId() {
        return playListId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTotalTalks() {
        return totalTalks;
    }

    public String getDescription() {
        return description;
    }

    public List<TalkVO> getTalksInPlayList() {
        return talksInPlayList;
    }
}
