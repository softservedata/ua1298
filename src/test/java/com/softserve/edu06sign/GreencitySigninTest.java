package com.softserve.edu06sign;

import com.google.gson.Gson;
import com.softserve.edu04par.SimpleJUnit5;
import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

class RunnerExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Boolean testResult = context.getExecutionException().isPresent();
        //System.out.println("\t\t\t\tException.isPresent() = " + testResult); //false - SUCCESS, true - FAILED
        //System.out.println("\t\t\t\tTest context.getDisplayName(): " + context.getDisplayName());
        //
        GreencitySigninTest.isTestSuccessful = !testResult;
    }
}

@ExtendWith(com.softserve.edu06sign.RunnerExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GreencitySigninTest {
    private static final String BASE_URL = "https://www.greencity.cx.ua/#/ubs";
    private static final String POST_URL = "https://greencity-user.greencity.cx.ua/api/testers/sign-in";
    private static final int MAX_IMPLICITLY_WAIT = 10;
    private static final Long ONE_SECOND_DELAY = 1000L;
    private static WebDriver driver;
    private static JavascriptExecutor js;
    private static OkHttpClient client;
    private static Gson gson;
    protected static Boolean isTestSuccessful = false;

    protected static void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    protected static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void setItemLocalStorage(String item, String value) {
        js.executeScript(String.format("window.localStorage.setItem('%s','%s');", item, value));
    }

    public void removeItemLocalStorage(String item) {
        js.executeScript(String.format("window.localStorage.removeItem('%s');", item));
    }

    @BeforeAll
    public static void setup() {
        // Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //
        // Firefox
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT)); // 0 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100)); // 30 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180)); // 300 by default
        driver.manage().window().maximize();
        //
        js = (JavascriptExecutor) driver;
        client = new OkHttpClient();
        gson = new Gson();
    }

    @AfterAll
    public static void tear() {
        presentationSleep(4); // For Presentation ONLY
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() {
        driver.get(BASE_URL);
        presentationSleep();
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        if (!isTestSuccessful) {
            // TODO use logging
            System.out.println("\t\t\tgetTestMethod = " + testInfo.getTestMethod());
            System.out.println("\t\t\tgetDisplayName = " + testInfo.getDisplayName());
        }
        // delete session
        driver.manage().deleteAllCookies(); // clear cache; delete cookie; delete session;
        removeItemLocalStorage("accessToken");
        removeItemLocalStorage("refreshToken");
        presentationSleep(); // For Presentation ONLY
    }

    @DisplayName("checkSignin()")
    @Test
    //@Order(1)
    public void checkSignin() throws IOException {
        // Click Signin button
        driver.findElement(By.cssSelector("div.main-content.app-container img.ubs-header-sing-in-img.ng-star-inserted")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Close popup window
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        if (iframe.size() > 0) {
            driver.switchTo().frame(iframe.get(0));
            List<WebElement> popupButton = driver.findElements(By.id("close"));
            if (popupButton.size() > 0) {
                popupButton.get(0).click();
            }
            driver.switchTo().defaultContent();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_IMPLICITLY_WAIT));
        presentationSleep(); // For Presentation ONLY
        //
        // Clear email placeholder
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        presentationSleep(); // For Presentation ONLY
        //
        // Type email
        driver.findElement(By.id("email")).sendKeys("tyv09754@zslsz.com");
        presentationSleep(); // For Presentation ONLY
        //
        // Clear password placeholder
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        presentationSleep(); // For Presentation ONLY
        //
        // Type password
        driver.findElement(By.id("password")).sendKeys("password");
        presentationSleep(); // For Presentation ONLY
        //
        // Activate Sign in Button
        js.executeScript("document.querySelector('button.ubsStyle').removeAttribute('disabled')");
        presentationSleep(); // For Presentation ONLY
        //
        driver.findElement(By.cssSelector("button.ubsStyle")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Signin by post method
        String jsonBody = new StringBuilder()
                .append("{")
                .append("\"email\":\"lsd09559@kisoq.com\",")
                .append("\"password\":\"Qwerty_1\",")
                .append("\"secretKey\":\"UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D\"")
                .append("}").toString();
        RequestBody requestBody = RequestBody.create(jsonBody,
                MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(POST_URL)
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String resultJson = response.body().string();
        SigninResponse signinResponse = gson.fromJson(resultJson, SigninResponse.class);
        //
        // Fill Local Storage
        setItemLocalStorage("accessToken", signinResponse.getAccessToken());
        setItemLocalStorage("language", "en");
        setItemLocalStorage("name", signinResponse.getName());
        setItemLocalStorage("refreshToken", signinResponse.getRefreshToken());
        setItemLocalStorage("userId", "3753");
        //
        // Refresh browser
        driver.navigate().refresh();
        presentationSleep(); // For Presentation ONLY
        //
        // Get name
        WebElement userName = driver.findElement(By.cssSelector("div.main-content.app-container li.ubs-user-name"));
        presentationSleep(); // For Presentation ONLY
        //
        // Check
        Assertions.assertEquals(signinResponse.getName(), userName.getText());
        presentationSleep(); // For Presentation ONLY
        //
        // Open dropdown
        userName.click();
        //driver.findElement(By.cssSelector("div.main-content.app-container li.ubs-user-name")).click();
        presentationSleep(); // For Presentation ONLY
        //
        // Signout
        driver.findElement(By.cssSelector("div.main-content.app-container li[aria-label='sign-out'] > a")).click();
        presentationSleep(); // For Presentation ONLY
    }

}
