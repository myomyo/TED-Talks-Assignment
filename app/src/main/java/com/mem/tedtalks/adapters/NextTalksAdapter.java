package com.mem.tedtalks.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mem.tedtalks.R;
import com.mem.tedtalks.data.vos.TalkVO;
import com.mem.tedtalks.viewholders.NextTalksViewHolder;

import java.util.List;

public class NextTalksAdapter extends RecyclerView.Adapter<NextTalksViewHolder> {

    private List<TalkVO> mTalkList;

    public void setTalkList(List<TalkVO> mTalkList) {
        this.mTalkList = mTalkList;
    }

    @Override
    public NextTalksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_next_talks, parent, false);
        return new NextTalksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NextTalksViewHolder holder, int position) {
        holder.setTalkData(mTalkList.get(position));
    }


    @Override
    public int getItemCount() {
        return mTalkList.size();
    }
}
