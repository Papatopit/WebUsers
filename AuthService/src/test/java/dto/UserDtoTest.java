package dto;


import org.authService.user.RoleDto;
import org.authService.user.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserDtoTest {
    public static final String firstName = "Jorik";
    public static final String lastName = "Vartanov";
    public static final String email = "vartanov@mail.ru";
    public static final String birthday = "2022-03-29";
    public static final String role = "USER";

    @Test
    void createNewUserDtoObject() {

        UserDto result = UserDto
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .email(email)
                .role(RoleDto.valueOf(role))
                .build();

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getFirstName());
        Assertions.assertNotNull(result.getLastName());
        Assertions.assertNotNull(result.getBirthday());
        Assertions.assertNotNull(result.getEmail());
        Assertions.assertNotNull(result.getRole());

        Assertions.assertSame(result.getEmail(),email);
        Assertions.assertSame(result.getLastName(),lastName);
        Assertions.assertSame(result.getFirstName(),firstName);
        Assertions.assertSame(result.getRole(),role);

        Assertions.assertEquals(result.getFirstName(), firstName);
        Assertions.assertEquals(result.getLastName(), lastName);
        Assertions.assertEquals(result.getEmail(), email);
        Assertions.assertEquals(result.getBirthday(), birthday);
        Assertions.assertEquals(result.getRole(), role);
    }
}