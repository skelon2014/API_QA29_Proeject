package schedulertests;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class DeleteRecordByIdTest {
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImtzZWxvbkBiay5ydSJ9.zWO5Co7fAnnMxz2Zlj65w2aj2YLynOuoQi9R79LG7Ss";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://super-scheduler-app.herokuapp.com/";
        RestAssured.basePath = "api";
    }

    @Test
    public void deeteRecorddsSucess(){
        String status = given().header("Authorization", token)
                .when()
                .delete("record/{id}", 19132)
                .then()
                .statusCode(400)
                .extract().path("status");
        System.out.println("=============");
        System.out.println(status);
        System.out.println("=============");

        given().header("Authorization", token)
                .when()
                .delete("record/{id}", 19201)
                .then()
                .statusCode(200)
                .assertThat()
                .body("status",containsString("was deleted"));

    }
}
