package com.mem.tedtalks.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.mem.tedtalks.R;
import com.mem.tedtalks.adapters.NextTalksAdapter;
import com.mem.tedtalks.data.models.TedTalkModel;
import com.mem.tedtalks.data.vos.TalkVO;
import com.mem.tedtalks.utils.GlideApp;
import com.mem.tedtalks.utils.TalkConstants;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class TalkDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_talk_details)
    TextView tvTalkDetails;

    @BindView(R.id.tv_speak_name)
    TextView tvSpeakerName;

    @BindView(R.id.tv_talk_title)
    TextView tvTalkTitle;

    @BindView(R.id.tv_about_speaker_name)
    TextView tvAboutSpeakerName;

    @BindView(R.id.tv_duration)
    TextView tvDuration;

    @BindView(R.id.iv_talk_backdrop)
    ImageView ivTalkBackDrop;

    @BindView(R.id.rv_talk_details)
    RecyclerView rvNextTalks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_details);


        ButterKnife.bind(this, this);

        //int defaultValue = 0;
        int talkId = getIntent().getIntExtra(TalkConstants.TALK_ID, -1);
       /* if(talkId != -1) {

        }*/
        Log.d("TalkDetailsActivity", "TalkId : " + talkId);

        TalkVO talk = TedTalkModel.getObjInstance().getTalkById(talkId); // get talk object

        List<TalkVO> talkList = TedTalkModel.getObjInstance().getTalkList(); // get the talk list from model
        Log.d("TalkDetailsActivity", "Talk list : "+talkList.size());

        bindData(talk, talkList);
    }

    private void bindData(TalkVO talk, List<TalkVO> talkList) {

        tvTalkTitle.setText(talk.getTitle());
        tvTalkDetails.setText(talk.getDescription());
        tvSpeakerName.setText(talk.getSpeaker().getName());
        tvAboutSpeakerName.setText(talk.getSpeaker().getName());
        tvDuration.setText(talk.secondsToString());
        //tvDuration.setText(tvDuration.getContext().getResources().getString(R.string.format_duration, talk.secondsToString()));
        GlideApp.with(ivTalkBackDrop.getContext())
                .load(talk.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(ivTalkBackDrop);

        NextTalksAdapter talksAdapter = new NextTalksAdapter();
        talksAdapter.setTalkList(talkList); // set the talk list to adapter
        rvNextTalks.setAdapter(talksAdapter);
        rvNextTalks.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }
}
