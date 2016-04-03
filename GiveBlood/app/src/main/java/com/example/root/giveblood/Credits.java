package com.example.root.giveblood;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.makeinfo.giveblood.R;


public class Credits extends Fragment {


    public Credits() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        getActivity().getActionBar().hide();
        return inflater.inflate(R.layout.fragment_credits, container, false);
    }


}
