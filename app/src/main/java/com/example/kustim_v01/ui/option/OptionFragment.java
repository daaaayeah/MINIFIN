package com.example.kustim_v01.ui.option;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.kustim_v01.R;
import com.example.kustim_v01.User;
public class OptionFragment extends Fragment {

    private OptionViewModel optionViewModel;
    TextView mypage_name;
    TextView mypage_score;
    View root;
    TextView completed_count;
    ImageView gitgraph;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        optionViewModel =
                ViewModelProviders.of(this).get(OptionViewModel.class);
        root = inflater.inflate(R.layout.fragment_option, container, false);

        mypage_name = root.findViewById(R.id.mypage_name);
        mypage_name.setText(User.name);


        if(User.promise2==true){

            gitgraph = root.findViewById(R.id.github1);
            gitgraph.setImageResource(R.drawable.github2);

            completed_count =root.findViewById(R.id.completed_count);
            completed_count.setText("1");

            mypage_score =root.findViewById(R.id.mypage_score);
            mypage_score.setText("5");
        }
        return root;
    }
}