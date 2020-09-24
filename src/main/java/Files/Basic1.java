package Files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;

// convert response into string format
public class Basic1 {


    public static void main(String args[])
        {
            // Todo auto-generated mathod stub
            // validate if add place API is working as expected

            //Add place API-> update place with new address ->Get place to validate if new address is present in response

            //given - All input Detail
            //when - submit the API
            //then - validate the response
            RestAssured.baseURI="https://rahulshettyacademy.com";
            String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                    .body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200)
                    .body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

            System.out.println(response);
            JsonPath js = new JsonPath(response);             // for parsing json
            String place_id = js.getString("place_id");

            System.out.println(place_id);

            //update place
            String newAddress = "Summer Walk Africa";

            given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                    .body("{\n" +
                            "\"place_id\":\""+place_id+"\",\n" +
                            "\"address\":\""+newAddress+"\",\n" +
                            "\"key\":\"qaclick123\"\n" +
                            "}")
                    .when().put("maps/api/place/update/json").
                    then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

                   // Get place
                  String getPlaceResponse= given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
                   .when().get("/maps/api/place/get/json")
                   .then().assertThat().log().all().statusCode(200).extract().response().asString();

                  // JsonPath js1 = new JsonPath(getPlaceResponse);
                   JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
                   String actualAddress = js1.getString("address");
            System.out.println(actualAddress);
            Assert.assertEquals(actualAddress, newAddress);
                             //cucumber junit/ testng


        }
    }
