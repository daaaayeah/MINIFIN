package com.example.kustim_v01.ui.notifications;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kustim_v01.R;
import com.example.kustim_v01.User;

public class NotificationsFragment extends Fragment {
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout.LayoutParams params;
    LinearLayout.LayoutParams params2;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        layout1 =root.findViewById(R.id.noti_layout1);
        params2 = (LinearLayout.LayoutParams) layout1.getLayoutParams();
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);
        layout1.setLayoutParams(params);
        layout3 = root.findViewById(R.id.noti_layout3);
        layout4 = root.findViewById(R.id.noti_layout4);

        if(User.promise2==true) {

            /*해당 margin값 변경*/
            params2.height=135;
            params2.width=500;
            params2.bottomMargin = 10;
            params2.topMargin = 10;

            /*변경된 값의 파라미터를 해당 레이아웃 파라미터 값에 셋팅*/
            layout1.setLayoutParams(params2);
            layout4.setLayoutParams(params);
        }
        return root;
    }
}