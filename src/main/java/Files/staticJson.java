package Files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import java.nio.file.Files;

public class staticJson {
    @Test
    public void addBook() throws IOException {

        // sending parameters to payload from Test
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json").
                body(GenerateStringFromResource("C:\\Users\\dushy\\Documents\\addbookdetail.json")).
                when().
                post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).
                extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println("id");
    }

    public static String GenerateStringFromResource(String path) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}