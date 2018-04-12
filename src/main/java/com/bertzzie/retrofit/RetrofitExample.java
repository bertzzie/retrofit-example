package com.bertzzie.retrofit;

import com.bertzzie.retrofit.models.Repo;
import com.bertzzie.retrofit.services.GithubService;

import java.io.IOException;
import java.util.List;

/**
 * [Documentation Here]
 *
 * @author Alex Xandra Albert Sim
 */
public class RetrofitExample {
    static GithubService service = GithubService.getClient();

    public static void main(String[] args) {
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
}
