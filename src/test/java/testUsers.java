import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RestAssuredResponseOptionsGroovyImpl;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.junit.jupiter.api.Assertions;
import static io.restassured.RestAssured.given;

public class testUsers {
    private static String Payload="{\n"+
            "   \"name\": \"morpheus\",\n" +
            "    \"job\":\"leader\"\n"+
            "}";

    @Test
    public void getUsers(){
        given().
                get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }
    @Test
    public void Post(){
        Response response=given().contentType(ContentType.JSON)
                .body(Payload)
                .post("https://reqres.in/api/users")
                .then().log().all().statusCode(201)
                .extract()
                .response();
        System.out.println(response.statusCode());
        Assert.assertEquals("leader",response.jsonPath().getString("job"));
        Assert.assertEquals("morpheus",response.jsonPath().getString("name"));



    }
}
