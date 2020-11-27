package com.example.kustim_v01.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kustim_v01.CompletedQuestActivity;
import com.example.kustim_v01.FirstlogoActivity;
import com.example.kustim_v01.MoneyDashActivity;
import com.example.kustim_v01.R;
import com.example.kustim_v01.SigninActivity;
import com.example.kustim_v01.SignupActivity;
import com.example.kustim_v01.User;
import com.example.kustim_v01.popup.PopupActivity;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

public class DashboardFragment extends Fragment {
    Button completed_button;
    Button continue_button;
    Button money_button;
    TextView dashboard_first;
    TextView dashboard_second;
    TextView dashboard_third;
    TextView dashboard_first2;
    TextView dashboard_second2;
    TextView dashboard_third2;
    ImageView dashboard_day;
    ImageView dashboard_day1;
    ImageView dashboard_day2;
    ImageView dashboard_check;
    ImageView dashboard_check1;
    ImageView dashboard_check2;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboard_first = root.findViewById(R.id.dashboard_first);
        dashboard_first2 = root.findViewById(R.id.dashboard_first2);
        dashboard_second = root.findViewById(R.id.dashboard_second);
        dashboard_second2 = root.findViewById(R.id.dashboard_second2);
        dashboard_third = root.findViewById(R.id.dashboard_third);
        dashboard_third2 = root.findViewById(R.id.dashboard_third2);
        dashboard_day = root.findViewById(R.id.dashboard_day);
        dashboard_day1 = root.findViewById(R.id.dashboard_day1);
        dashboard_day2 = root.findViewById(R.id.dashboard_day2);
        dashboard_check = root.findViewById(R.id.dashboard_check);
        dashboard_check1 = root.findViewById(R.id.dashboard_check1);
        dashboard_check2 = root.findViewById(R.id.dashboard_check2);

        continue_button = root.findViewById(R.id.continue_button);
        completed_button = root.findViewById(R.id.completed_button);
        money_button = root.findViewById(R.id.money_button);

    if (SigninActivity.user.promise == true & SigninActivity.user.money == false && SigninActivity.user.wakeup == false & SigninActivity.user.promise2 == false) {
            dashboard_first.setText("이번 주 약속 하나를 달성하세요!");
            dashboard_first2.setText("친구와의 약속 등록 후, 약속 장소에\n늦지 않고 도착하면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
        }
        if (SigninActivity.user.promise == true & SigninActivity.user.money == false && SigninActivity.user.wakeup == false & SigninActivity.user.promise2 == true) {
        }
        if (SigninActivity.user.promise == false & SigninActivity.user.money == true & SigninActivity.user.wakeup == false) {
            dashboard_first.setText("이번 주 짠돌이 되기 챌린지!");
            dashboard_first2.setText("일주일 예산 지출을 세워 일주일 후\n 목표 안에 들면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
        }
        if (SigninActivity.user.promise == false & SigninActivity.user.money == false & SigninActivity.user.wakeup == true ) {
            dashboard_first.setText("이번주 최고 성실맨이 되어라!");
            dashboard_first2.setText("7일 동안 7시 기상\n 3회 성공하면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
        }
        if (SigninActivity.user.promise == true & SigninActivity.user.money == true & SigninActivity.user.wakeup == false & SigninActivity.user.promise2 == false) {
            dashboard_first.setText("이번 주 약속 하나를 달성하세요!");
            dashboard_first2.setText("친구와의 약속 등록 후, 약속 장소에\n늦지 않고 도착하면 미션 완료!");
            dashboard_second.setText("이번 주 짠돌이 되기 챌린지!");
            dashboard_second2.setText("일주일 예산 지출을 세워 일주일 후\n 목표 안에 들면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_day1.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
            dashboard_check1.setVisibility(View.VISIBLE);
        }
        if (SigninActivity.user.promise == true & SigninActivity.user.money == true & SigninActivity.user.wakeup == false & SigninActivity.user.promise2 == true) {
            dashboard_first.setText("이번 주 짠돌이 되기 챌린지!");
            dashboard_first2.setText("일주일 예산 지출을 세워 일주일 후\n 목표 안에 들면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
        }
        if (SigninActivity.user.promise == false & SigninActivity.user.money == true & SigninActivity.user.wakeup == true & SigninActivity.user.promise2 == false) {
            dashboard_first.setText("이번 주 짠돌이 되기 챌린지!");
            dashboard_first2.setText("일주일 예산 지출을 세워 일주일 후\n 목표 안에 들면 미션 완료!");
            dashboard_second.setText("이번주 최고 성실맨이 되어라!");
            dashboard_second2.setText("7일 동안 7시 기상\n 3회 성공하면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_day1.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
            dashboard_check1.setVisibility(View.VISIBLE);

        }
        if (SigninActivity.user.promise == true & SigninActivity.user.money == false & SigninActivity.user.wakeup == true & SigninActivity.user.promise2 == false) {
            dashboard_first.setText("이번주 최고 성실맨이 되어라!");
            dashboard_first2.setText("7일 동안 7시 기상\n 3회 성공하면 미션 완료!");
            dashboard_second.setText("이번 주 약속 하나를 달성하세요!");
            dashboard_second2.setText("친구와의 약속 등록 후, 약속 장소에\n늦지 않고 도착하면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_day1.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
            dashboard_check1.setVisibility(View.VISIBLE);
        }

        if (SigninActivity.user.promise == true & SigninActivity.user.money == false & SigninActivity.user.wakeup == true & SigninActivity.user.promise2 == true) {
            dashboard_first.setText("이번주 최고 성실맨이 되어라!");
            dashboard_first2.setText("7일 동안 7시 기상\n 3회 성공하면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
        }


        if (SigninActivity.user.promise == true & SigninActivity.user.money == true & SigninActivity.user.wakeup == true & SigninActivity.user.promise2 == true) {
            dashboard_first.setText("이번 주 짠돌이 되기 챌린지!");
            dashboard_first2.setText("일주일 예산 지출을 세워 일주일 후\n 목표 안에 들면 미션 완료!");
            dashboard_second.setText("이번주 최고 성실맨이 되어라!");
            dashboard_second2.setText("7일 동안 7시 기상\n 3회 성공하면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_day1.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
            dashboard_check1.setVisibility(View.VISIBLE);
        }

        if (SigninActivity.user.promise == true & SigninActivity.user.money == true & SigninActivity.user.wakeup == true & SigninActivity.user.promise2 == false) {
            dashboard_first.setText("이번 주 약속 하나를 달성하세요!");
            dashboard_first2.setText("친구와의 약속 등록 후, 약속 장소에\n늦지 않고 도착하면 미션 완료!");
            dashboard_second.setText("이번 주 짠돌이 되기 챌린지!");
            dashboard_second2.setText("일주일 예산 지출을 세워 일주일 후\n 목표 안에 들면 미션 완료!");
            dashboard_third.setText("이번주 최고 성실맨이 되어라!");
            dashboard_third2.setText("7일 동안 7시 기상\n 3회 성공하면 미션 완료!");
            dashboard_day.setVisibility(View.VISIBLE);
            dashboard_day1.setVisibility(View.VISIBLE);
            dashboard_day2.setVisibility(View.VISIBLE);
            dashboard_check.setVisibility(View.VISIBLE);
            dashboard_check1.setVisibility(View.VISIBLE);
            dashboard_check2.setVisibility(View.VISIBLE);
        }


        Button completed_button = root.findViewById(R.id.completed_button);
        completed_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.completed_button) {
                    Intent intent = new Intent(getActivity(), CompletedQuestActivity.class);
                    startActivity(intent);
                }
            }
        }));

        Button money_button = root.findViewById(R.id.money_button);
        money_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.money_button) {
                    Intent intent = new Intent(getActivity(), MoneyDashActivity.class);
                    startActivity(intent);
                }
            }
        }));


        return root;
    }

}