package tests;

import dto.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.BaseTest.getRequest;

public class GetUserListTest {
@Test
    public void userList (){

    List <User> users =
            getRequest( "/user",200).body().jsonPath()
                    .getList("data", User.class);
    assertFalse(users.get(0).getFirstName().isEmpty());

    for (User user:
            users) {
        assertFalse(user.getFirstName().isEmpty());
    }

}



    }






