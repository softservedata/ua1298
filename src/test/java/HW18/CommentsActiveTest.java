package HW18;

import com.google.gson.Gson;
import okhttp3.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentsActiveTest {

    private Gson gson;
    private OkHttpClient client;
    private RequestBody requestBody;
    private Request request;
    private Response response;
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
    public void checkSignin() throws Exception {
        LoginRequest greencityLoginRequest = new LoginRequest("lsd09559@kisoq.com",
                "Qwerty_1",
                "UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D");

        json = gson.toJson(greencityLoginRequest);
        requestBody = RequestBody.create(json,
                MediaType.parse("application/json; charset=utf-8"));
        request = new Request.Builder()
                .url("https://greencity-user.greencity.cx.ua/api/testers/sign-in")
                .post(requestBody)
                .build();
        response = client.newCall(request).execute();

        assertTrue(response.isSuccessful());
        Assertions.assertEquals(response.code(), 200);
        json = response.body().string();
        LoginResponce greencityLoginResponce = gson.fromJson(json, LoginResponce.class);
        token = greencityLoginResponce.getAccessToken();

        System.out.println("Login Json = " + json);
        System.out.println("\nLogin Responce = " + greencityLoginResponce);
    }

    @Test
    @Order(2)
    public void getActiveComments() throws Exception {
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("https://greencity.greencity.cx.ua/eco-news/1957/comments/active")
                .newBuilder();
        String url = urlBuilder.build().toString();
        request = new Request
                .Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        response = client.newCall(request).execute();
        json = response.body().string();
        CommentsResponse commentsResponse = gson.fromJson(json, CommentsResponse.class);
        System.out.println("\nComment Json: " + json);

        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertNotNull(response.body());
        assertNotNull(commentsResponse);

        commentsResponse.printComments();
        System.out.println("Total Elements: " + commentsResponse.getTotalElements());
        System.out.println("Current Page: " + commentsResponse.getCurrentPage());
        System.out.println("Total Pages: " + commentsResponse.getTotalPages());
    }
}