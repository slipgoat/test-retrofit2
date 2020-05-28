import core.ApiClient;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    private ApiClient apiClient;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        apiClient = new ApiClient();
    }

    public ApiClient getApiClient() {
        return apiClient;
    }
}
