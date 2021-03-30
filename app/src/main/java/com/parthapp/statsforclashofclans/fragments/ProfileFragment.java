package com.parthapp.statsforclashofclans.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.parthapp.statsforclashofclans.BuildConfig;
import com.parthapp.statsforclashofclans.ClashAdapter;
import com.parthapp.statsforclashofclans.R;
import com.parthapp.statsforclashofclans.models.Player;
import com.parthapp.statsforclashofclans.models.Troop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String  TAG = "Profile";
    private final Gson gson = new Gson();
    private static String userTag = "";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        userTag = bundle.getString("userTag");
        Log.i(TAG,"HERE IN PROFILE: " + userTag);
        ClashAdapter clash = new ClashAdapter(BuildConfig.CLASH_API);
        try {
            Response resData = clash.makeThreadAPICall(getGamerTag(userTag), "players/");
            if (resData.isSuccessful()) {
                Player player = gson.fromJson(Objects.requireNonNull(resData.body()).string(), Player.class);
                List<Troop> heroes = player.getHeroes();
                for(int i = 0; i < heroes.size(); i++){
                    Log.i(TAG, heroes.get(i).getLevel().toString());
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private String getGamerTag(String userTag) {
        if(userTag.equals("")) {
            List<String> profileTag = new ArrayList<>();
            profileTag.add("#LP8C008UJ");
            profileTag.add("#PQJQYC9CQ");
            profileTag.add("#2JQ299028");
            Random getRand = new Random();
            return profileTag.get(getRand.nextInt(profileTag.size()));
        }
        else{
            return userTag;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}