package com.softserve.edu.homework18;


import com.google.gson.Gson;
import okhttp3.*;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreenCityTest {

    private Gson gson;
    private OkHttpClient client;
    //
    private RequestBody requestBody;
    private Request request;
    private Response response;
    //
    private String json;
    private String token;

    @BeforeAll
    public void setup() {
        gson = new Gson();
        client = new OkHttpClient();
    }

    @AfterAll
    public void tear() {
        response.close();
    }

    @Test
    public void checkSignInTesters() throws Exception {
        GreenCityLoginRequest greenCityLoginRequest = new GreenCityLoginRequest("tyv09754@zslsz.com",
                "Qwerty_1",
                "UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D");
        // Serialization
        json = gson.toJson(greenCityLoginRequest);
        //
        // Login
        requestBody = RequestBody.create(json,
                MediaType.parse("application/json; charset=utf-8"));
        request = new Request.Builder()
                .url("https://greencity-user.greencity.cx.ua/api/testers/sign-in")
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        response = client.newCall(request).execute();
        //
        // Check
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(response.code(), 200);
        //
        json = response.body().string();
        GreenCityLoginResponce greenCityLoginResponce = gson.fromJson(json, GreenCityLoginResponce.class);
        token = greenCityLoginResponce.getAccessToken();
        //
        System.out.println("resultJson = " + json);
        System.out.println("greencityLoginResponce = " + greenCityLoginResponce);
        //
        // Check greencityLoginResponce
    }

    @Test
    public void checkComments() throws Exception {
        // Get all Events
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("https://greencity.greencity.cx.ua/eco-news/1953/comments/active")
                .newBuilder();
        String url = urlBuilder.build().toString();
        //
        request = new Request
                .Builder()
                .url(url)
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();
        response = client.newCall(request).execute();
        //
        // Check
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(response.code(), 200);
        //
        json = response.body().string();
        GreenCityAllComments greenCityAllComments = gson.fromJson(json, GreenCityAllComments.class);
        //
        System.out.println("resultJson: " + json);
        System.out.println("greenCityAllComments: " + greenCityAllComments);
        //
        // Check GreenCityAllComments
    }
}