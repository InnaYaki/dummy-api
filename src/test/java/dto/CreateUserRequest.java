package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@AllArgsConstructor@NoArgsConstructor@Getter
public class CreateUserRequest {

    private String lastName;
    private String firstName;
    private String email;


}
