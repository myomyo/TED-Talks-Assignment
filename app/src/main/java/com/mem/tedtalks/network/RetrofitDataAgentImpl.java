package com.mem.tedtalks.network;

import com.mem.tedtalks.events.ApiErrorEvent;
import com.mem.tedtalks.events.SuccessGetTalkEvent;
import com.mem.tedtalks.network.response.GetTalkResponse;
import com.mem.tedtalks.utils.TalkConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;


import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements TalksDataAgent {

    private static RetrofitDataAgentImpl sObjInstance;

    private TalksApi mTalkApi;

    private RetrofitDataAgentImpl() {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TalkConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTalkApi = retrofit.create(TalksApi.class);
    }

    public static RetrofitDataAgentImpl getObjInstance() {
        if(sObjInstance == null){
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadTalksList(int page, final String accessToken) {
        Call<GetTalkResponse> loadTalkCall = mTalkApi.loadTalksList(accessToken, page);
        loadTalkCall.enqueue(new Callback<GetTalkResponse>() {
            @Override
            public void onResponse(Call<GetTalkResponse> call, Response<GetTalkResponse> response) {
                GetTalkResponse talksResponse = response.body();
                if(talksResponse != null && talksResponse.isResponseOK()){
                    SuccessGetTalkEvent event = new SuccessGetTalkEvent(talksResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                } else {
                    if(talksResponse == null){
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call.");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(talksResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }

            }

            @Override
            public void onFailure(Call<GetTalkResponse> call, Throwable t) {

                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
