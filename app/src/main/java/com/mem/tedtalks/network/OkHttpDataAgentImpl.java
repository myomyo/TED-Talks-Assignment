package com.mem.tedtalks.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mem.tedtalks.events.ApiErrorEvent;
import com.mem.tedtalks.events.SuccessGetTalkEvent;
import com.mem.tedtalks.network.response.GetTalkResponse;
import com.mem.tedtalks.utils.TalkConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements TalksDataAgent {

    private static  OkHttpDataAgentImpl sObjInstance;

    private OkHttpClient mHttpClient;

    public OkHttpDataAgentImpl() {
        mHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpDataAgentImpl getObjInstance() {
        if(sObjInstance == null){
            sObjInstance = new OkHttpDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {

                RequestBody formBody = new FormBody.Builder()
                        .add(TalkConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TalkConstants.PAGE, String.valueOf(page))
                        .build();

                Request request = new Request.Builder()
                        .url(TalkConstants.API_BASE + TalkConstants.GET_TALKS)
                        .post(formBody)
                        .build();

                try {
                    Response response = mHttpClient.newCall(request).execute();
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        return responseString;
                    }
                } catch (IOException e) {
                    Log.d("loadTalksList", e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);
                Gson gson = new Gson();
                GetTalkResponse talkResponse = gson.fromJson(responseString, GetTalkResponse.class);
                Log.d("onPostExecute", "Talks List Size : "+ talkResponse.getTedTalks().size());
                if(talkResponse.isResponseOK()){
                    SuccessGetTalkEvent event = new SuccessGetTalkEvent(talkResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent event = new ApiErrorEvent(talkResponse.getMessage());
                    EventBus.getDefault().post(event);
                }
            }
        }.execute();
    }
}
