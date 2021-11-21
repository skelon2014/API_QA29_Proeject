package schedulertests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import schedulerdto.AuthRequestDTO;
import schedulerdto.AuthResponseDTO;
import schedulerdto.ErrorDTO;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredLoginTest {
    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://super-scheduler-app.herokuapp.com/";
        RestAssured.basePath = "api";
    }

    @Test
    public void loginSuccessTest() {
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .email("kselon@bk.ru")
                .password("Qwerty$4")
                .build();

        AuthResponseDTO responseDTO = given()
                .contentType("application/json")
                .body(auth)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(200)
                .extract().response().as(AuthResponseDTO.class);

        System.out.println(responseDTO.getStatus());
        System.out.println(responseDTO.getToken());
        System.out.println(responseDTO.isRegistration());
    }

    @Test
    public void loginWrongPassword() {
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .email("kselon@bk.ru")
                .password("Qwerty4")
                .build();

        ErrorDTO errorDTO = given().contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("login")
                .then()
                .assertThat().statusCode(401)
                .extract().response().as(ErrorDTO.class);

        System.out.println(errorDTO.toString());
    }

    @Test
    public void loginWrongPassword2() {
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .email("kselon@bk.ru")
                .password("werty4")
                .build();

        String message = given().contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("login")
                .then()
               // .assertThat().statusCode(401)
                .extract().path("message");

        System.out.println(message);
    }
    @Test
    public void registrationSucces(){
        int i = (int) (System.currentTimeMillis()/360000%100);
        AuthRequestDTO auth = AuthRequestDTO.builder()
                .email("skelon"+i+"@bk.ru")
                .password("Qwerty$4")
                .build();
        given().contentType(ContentType.JSON)
                .body(auth)
                .when()
                .post("login")
                .then()
                .statusCode(200)
                .assertThat().body("status",containsString("Registration success"))
                .body("registration",equalTo(true));
    }

}
