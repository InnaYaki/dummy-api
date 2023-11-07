package tests;

import com.sun.net.httpserver.Authenticator;
import dto.CreateUserRequest;
import dto.SuccessesDeleteUserResponse;
import dto.UnSuccessfullDeleteUserError;
import dto.UserById;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.BaseTest.*;

public class DeleteUserTest {

    @Test
    public void deleteExistingUser() {

        CreateUserRequest requestBody =
                new CreateUserRequest("Shagal", "Mark", getRandomEmail());

        UserById userFullResponse =
                postRequest("/user/create", 200, requestBody)
                        .body().jsonPath().getObject("", UserById.class);

        SuccessesDeleteUserResponse response =
                deleteRequest("/user/" + userFullResponse.getId(), 200)
                        .body().jsonPath().getObject("", SuccessesDeleteUserResponse.class);
        //System.out.println(response.getId());
        assertEquals(userFullResponse.getId(), response.getId());


    }

    @Test
    public void deleteDeletedUser() {

        CreateUserRequest requestBody =
                new CreateUserRequest("Shagal", "Mark", getRandomEmail());

        UserById userFullResponse =
                postRequest("/user/create", 200, requestBody)
                        .body().jsonPath().getObject("", UserById.class);

        SuccessesDeleteUserResponse response =
                deleteRequest("/user/" + userFullResponse.getId(), 200)
                        .body().jsonPath().getObject("", SuccessesDeleteUserResponse.class);

        UnSuccessfullDeleteUserError responseError =
                deleteRequest("/user/" + userFullResponse.getId(), 404)
                .body().jsonPath().getObject("", UnSuccessfullDeleteUserError.class);

        assertEquals( "RESOURCE_NOT_FOUND",responseError.getError());
    }

    @Test
    public void deleteInvalidUser() {

        UnSuccessfullDeleteUserError responseError =
                deleteRequest("/user/" + 777777, 400)
                        .body().jsonPath().getObject("", UnSuccessfullDeleteUserError.class);

        assertEquals( "PARAMS_NOT_VALID",responseError.getError());

    }



}