package com.example.haryono.widanharyono_1202150110_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText todoName, todoDescription, todoPriority;
    DataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //mengakses id edit text pada layout
        todoName = (EditText) findViewById(R.id.addTodo);
        todoDescription = (EditText) findViewById(R.id.addDesciptin);
        todoPriority = (EditText) findViewById(R.id.addPriority);
        db = new DataHelper(this);
    }

    @Override
    public void onBackPressed() {//method back
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }


    public void addData(View view) {//method ketika tombol addData klik dnegan kondisi disisi semua
        if (db.inputdata(new Data(todoName.getText().toString(), todoDescription.getText().toString(), todoPriority.getText().toString()))){
            Toast.makeText(this, "Berhasil Input", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddActivity.this, MainActivity.class));
            this.finish();
        }else {
            //apabila kosong
            Toast.makeText(this, "Gagal", Toast.LENGTH_LONG).show();
            todoName.setText(null);
            todoDescription.setText(null);
            todoPriority.setText(null);
        }
    }

}
