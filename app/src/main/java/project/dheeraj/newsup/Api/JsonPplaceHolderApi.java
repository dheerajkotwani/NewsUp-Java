package project.dheeraj.newsup.Api;

import project.dheeraj.newsup.Model.Feed;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPplaceHolderApi {

    String BaseUrl = "https://newsapi.org/v2/";

    @GET("top-headlines?country=in&apiKey=2544f26a90c44ad2940a0d85cddbccc9")
    Call<Feed> getData();
}
