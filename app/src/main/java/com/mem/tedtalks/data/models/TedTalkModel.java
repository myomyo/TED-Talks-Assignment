package com.mem.tedtalks.data.models;

import com.mem.tedtalks.network.HttpUrlConnectionDataAgentImpl;
import com.mem.tedtalks.network.OkHttpDataAgentImpl;
import com.mem.tedtalks.network.RetrofitDataAgentImpl;
import com.mem.tedtalks.network.TalksDataAgent;

public class TedTalkModel {


    public static final  String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";
    private static TedTalkModel objInstanc;
    private TalksDataAgent mDataAgent;

    private TedTalkModel() {
        //mDataAgent = HttpUrlConnectionDataAgentImpl.getObjInstance();
        //mDataAgent = OkHttpDataAgentImpl.getObjInstance();
        mDataAgent = RetrofitDataAgentImpl.getObjInstance();
    }

    public static TedTalkModel getObjInstance(){
        if (objInstanc == null){
            objInstanc = new TedTalkModel();
        }

        return  objInstanc;
    }

    public  void loadTalksList(){
        mDataAgent.loadTalksList(1, DUMMY_ACCESS_TOKEN);

    }
}
