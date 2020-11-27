package com.example.kustim_v01.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kustim_v01.ConfigmenuActivity;
import com.example.kustim_v01.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HomeFragment extends Fragment {
    CountDownTimer countDownTimer;
    TextView timer;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        timer = root.findViewById(R.id.timer);

        countDownTimer = new CountDownTimer(60000*60*24,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(getTime());
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();
        
        return root;
    }

    private String getTime(){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int c_hour = calendar.get(Calendar.HOUR_OF_DAY);
        int c_min = calendar.get(Calendar.MINUTE);
        int c_sec = calendar.get(Calendar.SECOND);

        return (29-day) +"일 " + (23-c_hour) + "시간 " + (59-c_min) + "분 "+ (59-c_sec)+"초";
    }
}