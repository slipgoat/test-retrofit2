package services;

import models.PostModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface PostsService {
    @GET("/posts")
    Call<List<PostModel>> getPosts();

    @GET("/posts/{id}")
    Call<PostModel> getPost(@Path("id") Long id);

    @POST("/posts")
    Call<PostModel> createPost(@Body PostModel post);
}
