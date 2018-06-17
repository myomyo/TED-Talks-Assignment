package com.mem.tedtalks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mem.tedtalks.R;
import com.mem.tedtalks.data.vos.TalkVO;
import com.mem.tedtalks.delegates.TalkDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TalksViewHolder extends RecyclerView.ViewHolder {

    private TalkDelegate mTalkDelegate;
    private TalkVO mTalk;

    @BindView(R.id.tv_speaker_name)
    TextView tvSpeakerName;

    @BindView(R.id.tv_talk_title)
    TextView tvTalkTitle;

    @BindView(R.id.iv_talks_image)
    ImageView ivTalkImage;

    @BindView(R.id.tv_duration)
    TextView tvDuration;

    public TalksViewHolder(View itemView, TalkDelegate talkDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mTalkDelegate = talkDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTalkDelegate.onTapTalk();
            }
        });
    }

    public void setTalkData(TalkVO talk) {
        mTalk = talk;
        tvSpeakerName.setText(talk.getSpeaker().getName());
        tvTalkTitle.setText(talk.getTitle());
        tvDuration.setText(talk.getDurationInSec());
        Glide.with(ivTalkImage.getContext())
                .load(talk.getImageUrl())
                .into(ivTalkImage);
    }
}
