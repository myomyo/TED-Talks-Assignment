package com.mem.tedtalks.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mem.tedtalks.delegates.TalkDelegate;

public class TalksViewHolder extends RecyclerView.ViewHolder {

    private TalkDelegate mTalkDelegate;

    public TalksViewHolder(View itemView, TalkDelegate talkDelegate) {
        super(itemView);
        mTalkDelegate = talkDelegate;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTalkDelegate.onTapTalk();
            }
        });
    }
}
