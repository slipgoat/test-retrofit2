package core;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class CallExecutor {

    public static <M> Response<M> execute(Call<M> call) {
        try {
            return call.execute();
        } catch (IOException e) {
            throw new ApiException("There is exception during REST method call: " + e.getMessage());
        }
    }

}
