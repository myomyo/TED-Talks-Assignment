package com.mem.tedtalks.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mem.tedtalks.R;
import com.mem.tedtalks.adapters.NextTalksAdapter;

public class TalkDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_details);

        RecyclerView rvNextTalks = (RecyclerView) findViewById(R.id.rv_talk_details);
        NextTalksAdapter talksAdapter = new NextTalksAdapter();
        rvNextTalks.setAdapter(talksAdapter);
        rvNextTalks.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }
}
