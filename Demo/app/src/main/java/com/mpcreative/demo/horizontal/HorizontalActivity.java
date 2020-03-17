package com.mpcreative.demo.horizontal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.mpcreative.demo.R;

import java.util.ArrayList;
import java.util.List;

public class HorizontalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_);
        //recyclerview_tasks.setAdapter(new HorizontalAdapter(getItems()));
        //recyclerview_tasks.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            items.add("item " + i);
        }
        return items;
    }
}
