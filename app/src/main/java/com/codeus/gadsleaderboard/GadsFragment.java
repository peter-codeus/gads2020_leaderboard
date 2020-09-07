package com.codeus.gadsleaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GadsFragment extends Fragment {

    private View layoutView;
    private FragmentType fragmentType;
    private ProgressBar progressBar;
    private TextView loaderTextView;


    public GadsFragment(FragmentType fragmentType) {
        this.fragmentType = fragmentType;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.gads_fragment, container, false);
        layoutView = view;
        //Recycler view portion
        Retrofit retrofit = new Retrofit.Builder().baseUrl(GadsApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GadsApi gadsApi = retrofit.create(GadsApi.class);

        Call<List<GadsModel>> dataCall;
        if(this.fragmentType == FragmentType.LEARNER)
            dataCall = gadsApi.getTopLearners();
        else
            dataCall = gadsApi.getTopSkillIQ();

        progressBar = view.findViewById(R.id.progressBar);
        loaderTextView = view.findViewById(R.id.loader_textview);
        dataCall.enqueue(new Callback<List<GadsModel>>() {
            @Override
            public void onResponse(Call<List<GadsModel>> call, Response<List<GadsModel>> response) {
                if(response.isSuccessful())
                {
                    Log.i("LEARNER", new Gson().toJson(response.body()));
                    GadsListAdapter learnerListAdapter = new GadsListAdapter(getContext(), response.body(), fragmentType);
                    RecyclerView recyclerView = layoutView.findViewById(R.id.skill_iq_list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(learnerListAdapter);
                    loaderTextView.setVisibility(View.INVISIBLE);
                }
                progressBar.setVisibility(View.INVISIBLE);
                loaderTextView.setText("An Error Occured while loading the data. Please retry later");
            }

            @Override
            public void onFailure(Call<List<GadsModel>> call, Throwable t) {
                Log.i("LEARNER", "Request to server failed");
                loaderTextView.setText("Failed to Load Data: Please check your internet connection.");
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }
}
