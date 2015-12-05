package com.hankkin.GridViewDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class MyActivity extends Activity implements OnCustomItemClickListener{


    private LineGridView myGridView;
    private GridView myNorgridView;
    private MyGridAdapter adapter;
    private Button btnGrid,btnFGGrid;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btnFGGrid = (Button) findViewById(R.id.btn_fg_grid);
        btnGrid = (Button) findViewById(R.id.btn_grid);
        myGridView = (LineGridView) findViewById(R.id.my_gridview);
        myNorgridView = (GridView) findViewById(R.id.my_normal_gridview);

        adapter = new MyGridAdapter(MyActivity.this,MyActivity.this);
        myNorgridView.setAdapter(adapter);
        myNorgridView.setVisibility(View.VISIBLE);
        myGridView.setVisibility(View.GONE);

        btnFGGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new MyGridAdapter(MyActivity.this,MyActivity.this);
                myGridView.setAdapter(adapter);
                myNorgridView.setVisibility(View.GONE);
                myGridView.setVisibility(View.VISIBLE);
            }
        });
        btnGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter = new MyGridAdapter(MyActivity.this,MyActivity.this);
                myNorgridView.setAdapter(adapter);
                myNorgridView.setVisibility(View.VISIBLE);
                myGridView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getPostion() {
        return 0;
    }

    @Override
    public void onCustomItemClk(int i) {
        Toast.makeText(getApplicationContext(),"您点击的是第"+(i+1)+"个",Toast.LENGTH_SHORT).show();
    }
}
