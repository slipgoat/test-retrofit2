import core.SchemaValidator;
import models.PostModel;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class TestPosts extends BaseTest {
    @Test
    public void couldGetPosts() {
        Response<List<PostModel>> response = getApiClient().getPostsController().getPosts();
        assertThat("Status code 200", response.code(), equalTo(200));

        List<PostModel> posts = response.body();
        SchemaValidator.validateArray(getClass().getResourceAsStream("schemas/postListSchema.json"), posts.toString());
        assertThat("Posts list is not empty", posts, not(empty()));
    }

    @Test
    public void couldGetPost() {
        Response<PostModel> response = getApiClient().getPostsController().getPost(1L);
        assertThat("Status code 200", response.code(), equalTo(200));

        PostModel post = response.body();
        SchemaValidator.validateObject(getClass().getResourceAsStream("schemas/postSchema.json"), post.toString());
        assertThat("Post id", post.getId(), equalTo(1L));
    }

    @Test
    public void couldCreatePost() {
        String title = "Clickbait title";
        String body = "Nothing interesting...";
        Long userId = 1L;

        Response<PostModel> response = getApiClient().getPostsController().createPost(title, body, userId);
        assertThat("Status code", response.code(), equalTo(201));

        PostModel post = response.body();
        assertThat("Post title", post.getTitle(), equalTo(title));
        assertThat("Post body", post.getBody(), equalTo(body));
        assertThat("Post userId", post.getUserId(), equalTo(userId));
    }
}
