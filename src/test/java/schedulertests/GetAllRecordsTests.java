package schedulertests;

import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import schedulerdto.ErrorDTO;
import schedulerdto.GetAllRecordsDTO;
import schedulerdto.RecordDTO;
import schedulerdto.RequestGetAllRecords;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class GetAllRecordsTests {
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
    public void getAllContactsSuccess() throws IOException {
        RequestGetAllRecords bodyDTO = RequestGetAllRecords.builder()
                .monthFrom(2)
                .monthTo(12)
                .yearFrom(2021)
                .yearTo(2021)
                .build();

        RequestBody body = RequestBody.create(gson.toJson(bodyDTO), JSON);
        Request request = new Request.Builder()
                .url("http://super-scheduler-app.herokuapp.com/api/records")
                .addHeader("Authorization", token)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        GetAllRecordsDTO listRecords = gson.fromJson(response.body().string(), GetAllRecordsDTO.class);
        for (RecordDTO record : listRecords.getRecords()) {
            System.out.println(record.getId());
            System.out.println(record.getTitle());
            System.out.println(record.getTotalSalary());
            System.out.println(record.getDate());
            System.out.println(record.getTimeFrom());
            System.out.println(record.getTimeTo());
            System.out.println(record.getHours());
            System.out.println("=============");
        }
    }

    @Test
    public void getAllContactsWrongToken() throws IOException {
        RequestGetAllRecords bodyDTO = RequestGetAllRecords.builder()
                .monthFrom(2)
                .monthTo(12)
                .yearFrom(2021)
                .yearTo(2021)
                .build();

        RequestBody body = RequestBody.create(gson.toJson(bodyDTO), JSON);
        Request request = new Request.Builder()
                .url("http://super-scheduler-app.herokuapp.com/api/records")
                .addHeader("Authorization", "I1NiJ9.eyJlbWFpbCI6ImtzZWxvb")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());

        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);

        Assert.assertEquals(errorDTO.getMessage(), "Wrong authorization format");
        Assert.assertEquals(errorDTO.getCode(), 401);
        System.out.println(errorDTO.getCode());
        System.out.println(errorDTO.getMessage());
        System.out.println(errorDTO.getDetails());
    }

    @Test
    public void getAllRecordsRestAssured() {

   //     RestAssured.baseURI = "https://super-scheduler-app.herokuapp.com/";
   //     RestAssured.basePath = "api";

        RequestGetAllRecords bodyDTO = RequestGetAllRecords.builder()
                .monthFrom(2)
                .monthTo(12)
                .yearFrom(2021)
                .yearTo(2021)
                .build();

        GetAllRecordsDTO allRecordsDTO = given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(bodyDTO)
                .when()
                .post("records")
                .then().assertThat().statusCode(200)
                .extract().response().as(GetAllRecordsDTO.class);

        for (RecordDTO record : allRecordsDTO.getRecords()) {
            System.out.println(record.getBreaks());
            System.out.println(record.getCurrency());
            System.out.println(record.getId());
            System.out.println(record.getTitle());
            System.out.println(record.getTotalSalary());
            System.out.println(record.getDate());
            System.out.println(record.getTimeFrom());
            System.out.println(record.getTimeTo());
            System.out.println(record.getHours());
            System.out.println(record.getType());
            System.out.println(record.getWage());
            System.out.println("=============");
        }
    }
}
