import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SampleTest {



@Test
    public void sampleTest() {
    given().
            when().
            get("https://reqres.in/api/users?page=2").
            then().
            assertThat();
    System.out.println("passed");
}

}
