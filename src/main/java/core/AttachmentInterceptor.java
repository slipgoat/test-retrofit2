package core;

import io.qameta.allure.Attachment;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

import java.io.IOException;

public class AttachmentInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String requestBody;
        String responseBody;
        Request request = chain.request();
        Response response = chain.proceed(request);

        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            requestBody = buffer.readUtf8();
        } else {
            requestBody = null;
        }
        responseBody = response.body() == null ? null : response.body().string();

        attachRequest(request, requestBody);
        attachResponse(response, responseBody);

        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), responseBody)).build();
    }

    @Attachment(value = "Request")
    private String attachRequest(Request request, String requestBody) {
        return request.method() + " " + request.url() +
                "\n" +
                "Body: " +
                "\n" +
                requestBody;
    }

    @Attachment(value = "Response")
    private String attachResponse(Response response, String responseBody) {
        return "Status code: " +
                response.code() +
                "\n" +
                "Body: " +
                "\n" +
                responseBody;
    }
}
