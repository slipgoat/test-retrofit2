package core;

import config.Configuration;
import controllers.PostsController;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiClient {
    private final PostsController postsController;

    public ApiClient() {
        String baseUrl = Configuration.getApi().protocol() + "://" + Configuration.getApi().host();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(Configuration.getApi().readTimeoutSec(), TimeUnit.SECONDS)
                .addInterceptor(new AttachmentInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();

        postsController = new PostsController(retrofit);
    }

    public PostsController getPostsController() {
        return postsController;
    }
}
