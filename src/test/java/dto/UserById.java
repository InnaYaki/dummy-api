package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
class Location {
    public String street;
    public String city;
    public String state;
    public String country;
    public String timezone;
}
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class UserById {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String phone;
    private Location location;
    private String registerDate;
    private String updatedDate;
}
