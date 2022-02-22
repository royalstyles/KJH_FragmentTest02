package com.example.kjh_fragmenttest02;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.*;

import com.example.kjh_fragmenttest02.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> list;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(getClass().getName(), "onCreate()");

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        splash_screen.flag = true;
    }

    @Override
    protected void onStart() {
        Log.d(getClass().getName(), "onStart()");

        super.onStart();

//        list = new ArrayList<>();
//        list.add("아이템 1");
//        list.add("아이템 2");
//        list.add("아이템 3");
//        list.add("아이템 4");
//        list.add("아이템 5");
//        list.add("아이템 6");
//
//        ItemAdapter itemAdapter  = new ItemAdapter(list);
//        RecyclerView recyclerView = findViewById(R.id.home_recylerView);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    protected void onResume() {
        Log.d(getClass().getName(), "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(getClass().getName(), "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(getClass().getName(), "onStop()");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(getClass().getName(), "onRestart()");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(getClass().getName(), "onDestroy()");
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.d(getClass().getName(), "onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs)");
        return super.onCreateView(parent, name, context, attrs);
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        Log.d(getClass().getName(), "onBackPressed()");

        if (doubleBackToExitPressedOnce){
            MainActivity.this.finish();
            moveTaskToBack(true);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "종료를 하려면 한번더 눌러주세요.", Toast.LENGTH_LONG).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(getClass().getName(), "run()");
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}