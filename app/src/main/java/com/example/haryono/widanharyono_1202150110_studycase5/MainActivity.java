package com.example.haryono.widanharyono_1202150110_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataHelper db;
    RecyclerView mRecyclerView;
    DataAdapter mAdapter;
    ArrayList<Data> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recview); //mengakses recyclerview yang ada pada layout
        mList = new ArrayList<>();//membuat araylist baru
        db = new DataHelper(this);//memanggil method getData
        db.getData(mList);

        //menginisialisasi shared preference
        SharedPreferences SharedPreferences = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = SharedPreferences.getInt("BackGroundCollor", R.color.white);

        mAdapter = new DataAdapter(this, mList, color);//membuat mAdapter baru
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //menampilkan layoutnya
        mRecyclerView.setAdapter(mAdapter);//set mAdapter untuk recycler view

        deleteData();
    }


    public void deleteData(){ // method untuk menghapus data
        ItemTouchHelper.SimpleCallback touchcall = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Data current = mAdapter.getData(position);

                if(direction==ItemTouchHelper.RIGHT){//apabila item di swipe ke arah kanan
                    if(db.deletData(current.getTodoName())){
                        mAdapter.deleteData(position);//delete data
                    }
                }
            }
        };
        //menentukan itemtouchhelper untuk recycler view
        ItemTouchHelper touchhelp = new ItemTouchHelper(touchcall);
        touchhelp.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //method ketika memilih setting
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item); //mengembalikan nilai item
    }

    //method mengklik fab mengarah ke addactivity
    public void addTodo(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }
}