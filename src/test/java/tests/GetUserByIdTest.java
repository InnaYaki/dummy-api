package tests;


import dto.UserById;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static tests.BaseTest.getRequest;

public class GetUserByIdTest {
       String RequestUserId = "60d0fe4f5311236168a109ca";

    @Test
       public void getUserId (){
           UserById userId =
                   getRequest( "/user/"+ RequestUserId,200).body().jsonPath()
                           .getObject("",UserById.class);

        assertFalse(userId.getId().isEmpty());
        assertEquals(RequestUserId,userId.getId());
        assertTrue(userId.getPicture().endsWith(".jpg"),"Actual picture format is NOT png");
        assertTrue(userId.getEmail().endsWith("example.com"));
        assertTrue (userId.getDateOfBirth().startsWith("1996"));










       }





}


