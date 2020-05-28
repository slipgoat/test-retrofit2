package controllers;

import core.CallExecutor;
import io.qameta.allure.Step;
import models.PostModel;
import retrofit2.Response;
import retrofit2.Retrofit;
import services.PostsService;

import java.util.List;

public class PostsController {
    PostsService postsService;

    public PostsController(Retrofit retrofit) {
        postsService = retrofit.create(PostsService.class);
    }

    @Step("Get posts list")
    public Response<List<PostModel>> getPosts() {
        return CallExecutor.execute(postsService.getPosts());
    }

    @Step("Get post by id - {id}")
    public Response<PostModel> getPost(Long id) {
        return CallExecutor.execute(postsService.getPost(id));
    }

    @Step("Create post \"{title}\"")
    public Response<PostModel> createPost(String title, String body, Long userId) {
        PostModel post = new PostModel(title, body, userId);
        return CallExecutor.execute(postsService.createPost(post));
    }
}
