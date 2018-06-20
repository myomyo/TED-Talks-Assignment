package com.mem.tedtalks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mem.tedtalks.R;
import com.mem.tedtalks.data.vos.TalkVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NextTalksViewHolder extends RecyclerView.ViewHolder {

    private TalkVO mTalk;

    @BindView(R.id.tv_next_speaker_name)
    TextView tvSpeakerName;

    @BindView(R.id.tv_next_talk_title)
    TextView tvTalkTitle;

    @BindView(R.id.ib_talks)
    Button btnTalkImage;

    public NextTalksViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setTalkData(TalkVO talk) {
        mTalk = talk;
        tvSpeakerName.setText(talk.getSpeaker().getName());
        tvTalkTitle.setText(talk.getTitle());
        btnTalkImage.setText(talk.secondsToString());
       /* Glide.with(btnTalkImage.getContext())
                .load(talk.getImageUrl())
                .into(btnTalkImage);*/
    }
}
