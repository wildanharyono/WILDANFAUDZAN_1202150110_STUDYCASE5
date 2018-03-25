package com.example.haryono.widanharyono_1202150110_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    TextView sp;
    int colorid;
    AlertDialog.Builder mAlert;
    SharedPreferences.Editor SharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Pengaturan");

        mAlert = new AlertDialog.Builder(this);

        //menginisialisasi shared preference
        SharedPreferences SharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        this.SharedPreferences = SharedPreferences.edit();
        colorid = SharedPreferences.getInt("Colourground", R.color.white);
        sp = findViewById(R.id.shapecolor);
        sp.setText(getShapeColor(colorid));
    }

    //apabila tombol back di tekan
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//menjalankan merhod kembali
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }

    public String getShapeColor(int i){
        if (i==R.color.blue){
            return "Blue";
        }else if (i==R.color.green){
            return "Green";
        }else{
            return "Red";
        }
    }

    //mendapatkan id dari warna yang akan digunakan
    public int getColorid(int i){
        if (i==R.color.blue){
            return R.id.blue;
        }else if (i==R.color.green){
            return R.id.green;
        }else{
            return R.id.red;
        }
    }

    public void choosecolor(View view) {
        mAlert.setTitle("Shape Color");
        View view1 = getLayoutInflater().inflate(R.layout.color_setting, null);
        mAlert.setView(view1);

        final RadioGroup RadioGroup = view1.findViewById(R.id.radiocolor);
        RadioGroup.check(getColorid(colorid));

        mAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int RadioGroups = RadioGroup.getCheckedRadioButtonId();
                switch (RadioGroups){
                    case R.id.red:
                        colorid = R.color.red;
                        break;
                    case R.id.blue:
                        colorid = R.color.blue;
                        break;
                    case R.id.green:
                        colorid = R.color.green;
                        break;

                }
                sp.setText(getShapeColor(colorid));
                SharedPreferences.putInt("BackGroundCollor", colorid);
                SharedPreferences.commit();
            }
        });

        mAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mAlert.create().show();
    }
}