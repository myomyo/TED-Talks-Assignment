package com.mem.tedtalks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mem.tedtalks.R;
import com.mem.tedtalks.data.vos.TalkVO;
import com.mem.tedtalks.delegates.TalkDelegate;
import com.mem.tedtalks.viewholders.TalksViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TalksAdapter extends RecyclerView.Adapter<TalksViewHolder> {

    private TalkDelegate mTalkDelegate;
    private List<TalkVO> mTalkList;

    public TalksAdapter(TalkDelegate talkDelegate) {
        mTalkDelegate = talkDelegate;
        mTalkList = new ArrayList<>();
    }

    @Override
    public TalksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talks, parent, false);
        return new TalksViewHolder(view, mTalkDelegate);
    }

    @Override
    public void onBindViewHolder(TalksViewHolder holder, int position) {
        holder.setTalkData(mTalkList.get(position));

    }

    @Override
    public int getItemCount() {
        return mTalkList.size();
    }

    public void setTalkList(List<TalkVO> talkList) {
        this.mTalkList = talkList;
        notifyDataSetChanged();
    }
}
