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
        if (User.promise2 == true) {

            View root = inflater.inflate(R.layout.fragment_notifications2, container, false);

            return root;
        } else {
            View root = inflater.inflate(R.layout.fragment_notifications, container, false);

            return root;
        }
    }
}