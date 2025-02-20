package com.softserve.edu08rest;

import com.google.gson.Gson;
import okhttp3.*;
import org.junit.jupiter.api.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreencityTest {

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
    @Order(1)
    public void checkSigninTesters() throws Exception {
        GreencityLoginRequest greencityLoginRequest = new GreencityLoginRequest("tyv09754@zslsz.com",
                "Qwerty_1",
                "UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D");
        // Serialization
        json = gson.toJson(greencityLoginRequest);
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
        GreencityLoginResponce greencityLoginResponce = gson.fromJson(json, GreencityLoginResponce.class);
        token = greencityLoginResponce.getAccessToken();
        //
        System.out.println("resultJson = " + json);
        System.out.println("greencityLoginResponce = " + greencityLoginResponce);
        //
        // Check greencityLoginResponce
    }

    @Test
    @Order(2)
    public void checkEvents() throws Exception {
        // Get all Events
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("https://greencity.greencity.cx.ua/events")
                .newBuilder();
        urlBuilder.addQueryParameter("page", "0");
        urlBuilder.addQueryParameter("size", "5");
        String url = urlBuilder.build().toString();
        //
        request = new Request
                .Builder()
                .url(url)
                .addHeader("Accept", "*/*")
                //.addHeader("Authorization", "Bearer " + token)
                .get()
                .build();
        response = client.newCall(request).execute();
        //
        // Check
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(response.code(), 200);
        //
        json = response.body().string();
        GreencityAllEvents greencityAllEvents = gson.fromJson(json, GreencityAllEvents.class);
        //
        System.out.println("resultJson: " + json);
        System.out.println("greencityAllEvents: " + greencityAllEvents);
        //
        // Check greencityAllEvents
    }

    @Test
    @Order(2)
    public void checkComments() throws Exception {
        // Get all Events
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("https://greencity.greencity.cx.ua/habits/comments/8997")
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
        GreencityAllComments greencityAllComments = gson.fromJson(json, GreencityAllComments.class);
        //
        System.out.println("resultJson: " + json);
        System.out.println("greencityAllComments: " + greencityAllComments);
        //
        // Check greencityAllComments
    }

    @Test
    @Order(2)
    public void checkHabits() throws Exception {
        // Get all Events
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("https://greencity.greencity.cx.ua/habit")
                .newBuilder();
        urlBuilder.addQueryParameter("page", "0");
        urlBuilder.addQueryParameter("size", "5");
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
        GreencityAllHabits greencityAllHabits = gson.fromJson(json, GreencityAllHabits.class);
        //
        System.out.println("resultJson: " + json);
        System.out.println("greencityAllHabits: " + greencityAllHabits);
        //
        // Check greencityAllHabits
        Assertions.assertEquals(7, greencityAllHabits.getTotalPages());
    }
}
