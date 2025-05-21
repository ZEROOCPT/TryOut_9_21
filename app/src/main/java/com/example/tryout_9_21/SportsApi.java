package com.example.tryout_9_21;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SportsApi {
    @GET("/api/v1/json/3/search_all_teams.php?l=English%20Premier%20League")
    Call<TeamResponse> getTeamsByLeague();
    // @Query("l") = parameter "l" di URL, misalnya ?l=English Premier League

    // Endpoint: https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?s=Soccer&c=Spain
    // Method ini digunakan untuk mengambil daftar tim berdasarkan jenis olahraga dan negara
    @GET("/api/v1/json/3/search_all_teams.php?l=Spanish%20La%20Liga")
    Call<TeamResponse> getTeamsByCountry();
    // @Query("s") = parameter "s" di URL, misalnya ?s=Soccer
    // @Query("c") = parameter "c" di URL, misalnya &c=Spain
}
