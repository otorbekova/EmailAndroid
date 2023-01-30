package com.example.my_test;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.my_test.registrasiya.Reg_Fragment;
import com.example.my_test.tool.Prefs;

public class MainActivity extends AppCompatActivity {
    private TextView registr;
    private Fragment fragment_main;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_main, new Main_Fragment(), "TaG");
            transaction.commit();
        }
        preferences = getPreferences(MODE_PRIVATE);
        boolean isShow = new Prefs(this).isShown();
        if (!isShow) {
            Reg_Fragment frd = new Reg_Fragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_main, frd, "TjjG");
            transaction.commit();
        }


    }


}