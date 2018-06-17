package com.mem.tedtalks.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mem.tedtalks.events.ApiErrorEvent;
import com.mem.tedtalks.events.SuccessGetTalkEvent;
import com.mem.tedtalks.network.response.GetTalkResponse;
import com.mem.tedtalks.utils.TalkConstants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionDataAgentImpl implements TalksDataAgent {

    private static HttpUrlConnectionDataAgentImpl objInstance;


    public HttpUrlConnectionDataAgentImpl() {
    }

    public static HttpUrlConnectionDataAgentImpl getObjInstance(){
        if(objInstance == null){
            objInstance = new HttpUrlConnectionDataAgentImpl();
        }
        return objInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {

                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {

                    // create the HttpURLConnection
                    url = new URL(TalkConstants.API_BASE + TalkConstants.GET_TALKS);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                    // just want to do an HTTP POST here
                    connection.setRequestMethod("POST");

                    // give it 15 seconds to respond
                    connection.setReadTimeout(15 * 1000);

                    connection.setDoInput(true);

                    connection.setDoOutput(true);

                    //put the request parameter into NameValuePair list.
                    List<NameValuePair> params = new ArrayList<>();

                    params.add(new BasicNameValuePair(TalkConstants.ACCESS_TOKEN, accessToken));

                    params.add(new BasicNameValuePair(TalkConstants.PAGE, String.valueOf(page)));

                    //write the parameters from NameValuePair list into connection obj.
                    OutputStream outputStream = connection.getOutputStream();

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();
                    connection.connect();

                    // read the output from the server
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null){
                        stringBuilder.append(line +"\n");

                    }

                    String responseString = stringBuilder.toString();

                    return responseString;


                } catch (Exception e) {
                    Log.e("", e.getMessage());
                } finally {
                    // close the reader; this can throw an exception too, so
                    // wrap it in another try/catch block.
                    if(reader != null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
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

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
