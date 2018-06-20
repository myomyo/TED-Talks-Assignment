package com.mem.tedtalks.data.models;

import com.mem.tedtalks.data.vos.TalkVO;
import com.mem.tedtalks.events.SuccessGetTalkEvent;
import com.mem.tedtalks.network.RetrofitDataAgentImpl;
import com.mem.tedtalks.network.TalksDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TedTalkModel {


    public static final  String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static TedTalkModel objInstance;
    private TalksDataAgent mDataAgent;
    private Map<Integer, TalkVO> mTalkMap;
    private List<TalkVO> mTalkList;


    private TedTalkModel() {
        //mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
        //mDataAgent = OkHttpDataAgentImpl.getObjInstance();
        mDataAgent = RetrofitDataAgentImpl.getObjInstance();
        mTalkMap = new HashMap<>();
        mTalkList = new ArrayList<>();
        EventBus.getDefault().register(this);

    }

    public static TedTalkModel getObjInstance(){
        if (objInstance == null){
            objInstance = new TedTalkModel();
        }

        return objInstance;
    }

    public  void loadTalksList(){
        mDataAgent.loadTalksList(1, DUMMY_ACCESS_TOKEN);

    }

    public TalkVO getTalkById(int talkId){
        return mTalkMap.get(talkId);
    }

    public List<TalkVO> getTalkList() {
        return mTalkList;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTalks(SuccessGetTalkEvent event){

        for(TalkVO talk : event.getTalkList()){
            mTalkMap.put(talk.getTalkId(), talk);
        }
        mTalkList = event.getTalkList();
    }
}
