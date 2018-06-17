package com.mem.tedtalks.events;

import com.mem.tedtalks.data.vos.TalkVO;

import java.util.List;

public class SuccessGetTalkEvent {

    private List<TalkVO> talkList;

    public SuccessGetTalkEvent(List<TalkVO> talkList) {
        this.talkList = talkList;
    }

    public List<TalkVO> getTalkList() {
        return talkList;
    }
}
