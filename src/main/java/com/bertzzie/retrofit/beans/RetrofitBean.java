package com.bertzzie.retrofit.beans;

import retrofit2.Retrofit;

import com.bertzzie.retrofit.services.GithubService;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * [Documentation Here]
 *
 * @author Alex Xandra Albert Sim
 */
public class RetrofitBean {
    // you'll probably want to use dependency injection here
    // i.e. see dagger if on android, guice / spring-di for others
    public static Retrofit getGithubClientRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(GithubService.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}
