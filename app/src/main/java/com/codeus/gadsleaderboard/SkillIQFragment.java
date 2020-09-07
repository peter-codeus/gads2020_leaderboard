package com.codeus.gadsleaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SkillIQFragment extends Fragment {

    private View layoutView;

    public SkillIQFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skill_i_q, container, false);
        //Recycler view portion
        layoutView = view;
        //Recycler view portion
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(GadsApi.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        GadsApi gadsApi = retrofit.create(GadsApi.class);
//
//        Call<List<GadsModel>> topLearnersCall = gadsApi.getTopSkillIQ();
//
//        topLearnersCall.enqueue(new Callback<List<GadsModel>>() {
//            @Override
//            public void onResponse(Call<List<GadsModel>> call, Response<List<GadsModel>> response) {
//                if(response.isSuccessful())
//                {
//                    Log.i("LEARNER", new Gson().toJson(response.body()));
//                    LearnerListAdapter learnerListAdapter = new LearnerListAdapter(getContext(), response.body(), fragme);
//                    RecyclerView recyclerView = layoutView.findViewById(R.id.skill_iq_list);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                    recyclerView.setAdapter(learnerListAdapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<GadsModel>> call, Throwable t) {
//                Log.i("LEARNER", "Request to server failed");
//            }
//        });

        return view;
    }
}
