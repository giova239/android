package com.example.steva.zombielabyrinth;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by steva on 29-Mar-18.
 */

public class cell extends Fragment {

    View v;

    public cell(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("debug", this.getArguments().getString("layout"));

            switch (this.getArguments().getString("layout")) {


                case "labyrinth0001":
                    v = inflater.inflate(R.layout.fragment_labyrinth0001, container, false);
                    break;
                case "labyrinth0010":
                    v = inflater.inflate(R.layout.fragment_labyrinth0010, container, false);
                    break;
                case "labyrinth0011":
                    v = inflater.inflate(R.layout.fragment_labyrinth0011, container, false);
                    break;
                case "labyrinth0100":
                    v = inflater.inflate(R.layout.fragment_labyrinth0100, container, false);
                    break;
                case "labyrinth0110":
                    v = inflater.inflate(R.layout.fragment_labyrinth0110, container, false);
                    break;
                case "labyrinth0111":
                    v = inflater.inflate(R.layout.fragment_labyrinth0111, container, false);
                    break;
                case "labyrinth1000":
                    v = inflater.inflate(R.layout.fragment_labyrinth1000, container, false);
                    break;
                case "labyrinth1001":
                    v = inflater.inflate(R.layout.fragment_labyrinth1001, container, false);
                    break;
                case "labyrinth1011":
                    v = inflater.inflate(R.layout.fragment_labyrinth1011, container, false);
                    break;
                case "labyrinth1100":
                    v = inflater.inflate(R.layout.fragment_labyrinth1100, container, false);
                    break;
                case "labyrinth1101":
                    v = inflater.inflate(R.layout.fragment_labyrinth1101, container, false);
                    break;
                case "labyrinth1110":
                    v = inflater.inflate(R.layout.fragment_labyrinth1110, container, false);
                    break;
                case "labyrinth1111":
                    v = inflater.inflate(R.layout.fragment_labyrinth1111, container, false);
                    break;
                case "labyrinth1010":
                    v = inflater.inflate(R.layout.fragment_labyrinth1010, container, false);
                    break;
                case "labyrinth0101":
                    v = inflater.inflate(R.layout.fragment_labyrinth0101, container, false);
                    break;
            }

            if(this.getArguments().getBoolean("start")){
                v.findViewById(R.id.start).setVisibility(View.VISIBLE);
            }
            if(this.getArguments().getBoolean("end")){
                v.findViewById(R.id.end).setVisibility(View.VISIBLE);
            }

        return v;
    }

    public cell getFragment(String name, Boolean start, Boolean end) {
        cell fragment = new cell();
        Bundle bundle = new Bundle();
        bundle.putString("layout", name);
        bundle.putBoolean("start", start);
        bundle.putBoolean("end", end);
        fragment.setArguments(bundle);
        return fragment;
    }
}
