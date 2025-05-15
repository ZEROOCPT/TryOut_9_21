package com.example.tryout_9_21;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentLaliga extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar pb;
    private TeamAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_laliga, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewTeams);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        pb = view.findViewById(R.id.pb);

        fetchTeams();
        return view;
    }

    private void fetchTeams() {
        SportsApi api = ApiClient.getClient().create(SportsApi.class);
        Call<TeamResponse> call = api.getTeamsByCountry();

        pb.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Team> teams = response.body().getTeams();
                    adapter = new TeamAdapter(teams);
                    recyclerView.setAdapter(adapter);

                    recyclerView.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.GONE);
            }
        });
    }
}
