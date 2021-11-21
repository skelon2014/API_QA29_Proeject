package contacttests;

import com.google.gson.Gson;
import contactdto.ContactDTO;
import contactdto.GetAllContactDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OkHttpGetAllContactsTest {
    @Test
    public void getAllContacts() throws IOException {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .addHeader("Authorization",
                        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6ImtzZWxvbis0QGJrLnJ1In0.iLacvZJY0UJz6OCOV8jRUuUfx9JiWpFzZsY6Dn9XQSU")
                .build();
        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());

  //      String responseJson = response.body().string();
   //     System.out.println(responseJson);

        if(response.isSuccessful()){
            GetAllContactDTO contacts = gson.fromJson(response.body().string(),GetAllContactDTO.class);
            for(ContactDTO contact : contacts.getContacts()){
                System.out.println(contact.getId());
                System.out.println(contact.getName());
                System.out.println(contact.getLastName());
                System.out.println(contact.getEmail());
                System.out.println(contact.getAddress());
                System.out.println(contact.getPhone());
                System.out.println(contact.getDescription());
                System.out.println("++++++++++++++++++++++");
            }
        }
    }
}
