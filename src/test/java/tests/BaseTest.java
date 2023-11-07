package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseTest {

    final static String BASE_URI = "https://dummyapi.io/data/v1/";
    final static String APP_ID_VALUE = "648f7664cb960eb131dfe4bf";

    static RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("app-id",APP_ID_VALUE)
            .build();

    public static Response getRequest (String endPoint,Integer responseCode){
        Response response = given()
                .spec(specification)
                .when()
                .log().all()
                .get(endPoint)
                .then()
                .log().all()
                .statusCode(responseCode)
                .extract().response();
        return response;
    }
   public static Response postRequest (String endPoint,Integer responseCode,Object body){
       Response response = given()
               .spec(specification)
               .body(body)
               .when()
               .log().all()
               .post(endPoint)
               .then()
               .log().all()
               .statusCode(responseCode)
               .extract().response();
       return response;
   }
    public static String getRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                //"abcdefghijklmopqrstuwxyz1234567890";
                //"ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + "@gmail.com";
    }
    public static Response deleteRequest (String endPoint,Integer responseCode){
        Response response = given()
                .spec(specification)
                .when()
                .log().all()
                .delete(endPoint)
                .then()
                .log().all()
                .statusCode(responseCode)
                .extract().response();
        return response;
    }
    }





