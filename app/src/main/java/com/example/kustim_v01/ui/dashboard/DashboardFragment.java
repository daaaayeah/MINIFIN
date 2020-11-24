package com.example.kustim_v01.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.kustim_v01.R;
import com.example.kustim_v01.SigninActivity;
import com.example.kustim_v01.SignupActivity;
import com.example.kustim_v01.User;
import com.example.kustim_v01.popup.PopupActivity;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashboardFragment extends Fragment{
    Button completed_button;
    Button continue_button;
    TextView dashboard_first;
    TextView dashboard_second;
    TextView dashboard_third;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboard_first =root.findViewById(R.id.dashboard_first);
        dashboard_second =root.findViewById(R.id.dashboard_second);
        dashboard_third = root.findViewById(R.id.dashboard_third);

        continue_button = root.findViewById(R.id.continue_button);
        completed_button = root.findViewById(R.id.completed_button);
        if(User.promise==true &User.money==false&&User.wakeup==false) {
            dashboard_first.setText("약속왕이 되어라!");
        }
        if(User.promise==false&User.money==true&User.wakeup==false) {
            dashboard_first.setText("짠돌이가 되어라!");
        }
        if(User.promise==false&User.money==false&User.wakeup==true) {
            dashboard_first.setText("성실맨이 되어라!");
        }
        if(User.promise==true&User.money==true&User.wakeup==false) {
            dashboard_first.setText("약속왕이 되어라!");
            dashboard_second.setText("짠돌이가 되어라!");
        }
        if(User.promise==false&User.money==true&User.wakeup==true) {
            dashboard_first.setText("이번 주 짠돌이 되기 챌린지!");
            dashboard_second.setText("이번주 최고 성실맨이 되어라!");

        }
        if(User.promise==true&User.money==false&User.wakeup==true){
            dashboard_first.setText("성실맨이 되어라!");
            dashboard_second.setText("약속왕이 되어라!!");
        }
        if(User.promise==true&User.money==true&User.wakeup==true) {
            dashboard_first.setText("약속왕이 되어라!");
            dashboard_second.setText("짠돌이가 되어라!");
            dashboard_third.setText("성실맨이 되어라!");

        }




        Button completed_button = root.findViewById(R.id.completed_button);
        completed_button.setOnClickListener((new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                if(v.getId()==R.id.completed_button){
                    Intent intent = new Intent(getActivity(), CompletedQuestActivity.class);
                    startActivity(intent);
                }
            }
        }));








        return root;
    }

    }

