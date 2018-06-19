package com.mem.tedtalks.network;

import com.mem.tedtalks.network.response.GetTalkResponse;
import com.mem.tedtalks.utils.TalkConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TalksApi {

    @FormUrlEncoded
    @POST(TalkConstants.GET_TALKS)
    Call<GetTalkResponse> loadTalksList(
            @Field(TalkConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TalkConstants.PAGE) int page);
}
