import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import Files.Payload;


public class Basics {

    public static void main(String args[])
    {
        Response response;
        // Todo auto-generated mathod stub
        // validate if add place API is working as expected

        //given - All input Detail
        //when - submit the API
        //then - validate the response
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
                .body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)");


        //Add place API-> update place with new address ->Get place to validate if new address is present in response



    }
}
