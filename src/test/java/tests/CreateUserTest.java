package tests;

import dto.CreateUserRequest;
import dto.UnSuccessCreateUserResponse;
import dto.UserById;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.getRandomEmail;
import static tests.BaseTest.postRequest;

public class CreateUserTest {
    @Test
    public void successCreateUser() {
        CreateUserRequest requestBody =
                new CreateUserRequest("Shagal", "Mark", getRandomEmail());

        UserById userFullResponse =
                postRequest("/user/create", 200, requestBody)
                        .body().jsonPath().getObject("", UserById.class);

        //CreateUserRequest requestBody = CreateUserRequest.builder()
        //  .firstName("David")
        // .lastName("Black")
        // .email(getRandomEmail())
        //  .build();
        //System.out.println(userFullResponse.getRegisterDate());


        assertFalse(userFullResponse.getFirstName().isEmpty(), "firstname is empty");
        assertTrue(!userFullResponse.getLastName().isEmpty());
        assertEquals(requestBody.getFirstName(), userFullResponse.getFirstName());
        assertEquals(requestBody.getLastName(), userFullResponse.getLastName());

        assertEquals(requestBody.getEmail().toLowerCase(), userFullResponse.getEmail().toLowerCase());
        assertEquals(userFullResponse.getRegisterDate(), userFullResponse.getRegisterDate());
        assertEquals(userFullResponse.getUpdatedDate(), userFullResponse.getUpdatedDate());
    }

    @Test
    public void unSuccessCreateUserWithOutEmail() {

        CreateUserRequest requestBody1 =
                new CreateUserRequest("Shagal", "Mark", "");

        UnSuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody1)
                        .body().jsonPath().getObject("data", UnSuccessCreateUserResponse.class);

        assertEquals("Path `email` is required.", response.getEmail());

    }

    @Test
    public void unSuccessCreateUserWithOutFirstName() {

        CreateUserRequest requestBody =
                new CreateUserRequest("Shagal", "", getRandomEmail());

        UnSuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody)
                        .body().jsonPath().getObject("data", UnSuccessCreateUserResponse.class);

        assertEquals("Path `firstName` is required.", response.getFirstName());

    }


    @Test
    public void unSuccessCreateUserWithOutLastName() {

        CreateUserRequest requestBody =
                new CreateUserRequest("", "Mark", getRandomEmail());

        UnSuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody)
                        .body().jsonPath().getObject("data", UnSuccessCreateUserResponse.class);

        assertEquals("Path `lastName` is required.",response.getLastName());
    }

    @Test
    public void unSuccessCreateUserWithOutRequiredFields() {

        CreateUserRequest requestBody =
                new CreateUserRequest("", "", "");

        UnSuccessCreateUserResponse response =
                postRequest("/user/create", 400, requestBody)
                        .body().jsonPath().getObject("data", UnSuccessCreateUserResponse.class);

        assertEquals("Path `email` is required.", response.getEmail());
        assertEquals("Path `lastName` is required.",response.getLastName());
        assertEquals("Path `firstName` is required.", response.getFirstName());
    }








}







