package com.bertzzie.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

/**
 * [Documentation Here]
 *
 * @author Alex Xandra Albert Sim
 */
public class RetrofitExampleSingleFile {
    public static void main(String[] args) {
        GithubService service = GithubService.getClient();

        String username = "bertzzie";

        try {
            List<Repo> repos = service.listRepos(username).execute().body();

            if (repos.size() > 0) {
                repos.stream().forEach(repo -> System.out.println(repo.getName()));
            } else {
                System.out.println(String.format("User %s has no repo", username));
            }

        } catch (IOException exception) {
            System.out.println("Exception on calling API");
            exception.printStackTrace();
        }
    }

    interface GithubService {
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

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true) // too lazy to write it all
    static class Repo {
        private Long id;

        private String name;

        private String url;
    }

    public static class RetrofitBean {
        // you'll probably want to use dependency injection here
        // i.e. see dagger if on android, guice / spring-di for others
        public static Retrofit getGithubClientRetrofit() {
            return new Retrofit.Builder()
                    .baseUrl(GithubService.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
    }
}
