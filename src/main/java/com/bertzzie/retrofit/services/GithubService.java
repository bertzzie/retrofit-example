package com.bertzzie.retrofit.services;

import com.bertzzie.retrofit.beans.RetrofitBean;
import com.bertzzie.retrofit.models.Repo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * [Documentation Here]
 *
 * @author Alex Xandra Albert Sim
 */
public interface GithubService {
    static final String BASE_URL = "https://api.github.com";

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    // you'll probably want to use dependency injection here
    // i.e. see dagger if on android, guice / spring-di for others
    static GithubService getClient() {
        Retrofit retrofit = RetrofitBean.getGithubClientRetrofit(); // ideally, wire this via DI

        return retrofit.create(GithubService.class);
    }
}
