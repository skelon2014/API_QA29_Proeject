package contacttests;

import com.google.gson.Gson;
import contactdto.AuthRequestDTO;
import contactdto.AuthResponseDTO;
import contactdto.ErrorDTO;
import okhttp3.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttpLoginTest {

    public static final MediaType JSON =
            MediaType.get("application/json; charset=utf-8");

    @Test
    public void loginTest() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .email("kselon+4@bk.ru")
                .password("Qwerty$4").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody).build();

        Response response = client.newCall(request).execute();

        String responseJson = response.body().string();
        System.out.println("+++"+responseJson);

        if(response.isSuccessful()){
       //     String responseJson = response.body().string();
            System.out.println(response.code());
            System.out.println(responseJson);
            System.out.println("============================");
            AuthResponseDTO responseDTO = gson.fromJson(responseJson,AuthResponseDTO.class);
            String token = responseDTO.getToken();
            System.out.println(token);
        }else {
            System.out.println("Response code ---> " + response.code());
          //  ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
            ErrorDTO errorDTO = gson.fromJson(responseJson,ErrorDTO.class);
            System.out.println("Code ----> " + errorDTO.getCode());
            System.out.println("Message -> " + errorDTO.getMessage());
            System.out.println("Details -> " + errorDTO.getDetails());
        }
    }
}
