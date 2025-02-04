package com.softserve.framework.lib;

import com.google.gson.Gson;
import com.softserve.framework.data.User;
import com.softserve.framework.data.UserResponse;
import okhttp3.*;

import java.io.IOException;

public class RegisterRest {

    private OkHttpClient client;
    private Gson gson;

    public RegisterRest() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public UserResponse signinPost(User user, String urlPost) {
        // Signin by post method
        String jsonBody = gson.toJson(user);
        RequestBody requestBody = RequestBody.create(jsonBody,
                MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(urlPost)
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        Response response = null;
        String resultJson = null;
        try {
            response = client.newCall(request).execute();
            resultJson = response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserResponse userResponse = gson.fromJson(resultJson, UserResponse.class);
        //
        return userResponse;
    }
}
