package Files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class DynamicJson {
    @Test(priority = 1, dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {

        // sending parameters to payload from Test
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all().header("Content-Type", "application/json").
                body(Payload.addBook(isbn, aisle)).
                when().
                post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).
                extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println("id");

        //deleteBook
    }


    /*
2  @Test()
    public void addBook()
    {
     /*1 RestAssured.baseURI="http://216.10.245.166";
         String response= given().log().all().header("Content-Type","application/json").body(Payload.addBook()).
                  when().
                  post("Library/Addbook.php").
                  then().log().all().assertThat().statusCode(200).
                  extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println("id");  1*/

    // sending parameters to payload from Test
      /*2  RestAssured.baseURI="http://216.10.245.166";
        String response= given().log().all().header("Content-Type","application/json").
                body(Payload.addBook("adsfs", "6464")).
                when().
                post("Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).
                extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println("id");
             //deleteBook
    } 2 */


    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        //Array = collection of elements
        //Multiple Array= Collection of array
        return new Object[][]{{"ojtwfyzzz", "9346"}, {"jhdjkhzzz", "4256"}, {"chjxbzzz", "6346"}};
    }

    @Test()
    public void deleteData()
    {
          RestAssured.baseURI = "http://216.10.245.166";
        String response =  given().header("Content-Type", "application/json").queryParam("isbn","ojtwfyzzz").
                when().
                delete("Library/DeleteBook.php/").
                then().log().all().assertThat().statusCode(200).
                extract().
                response().asString();


    }

    public static String GenerateStringFromResource(String path) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
