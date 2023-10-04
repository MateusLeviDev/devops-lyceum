package nvoip.api.requests.User;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserPostRequestBody {

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Name cannot be null")
    private String email;
    @NotNull(message = "Numbersip cannot be null")
    private String numbersip;
    private String userToken;
    private String telephone;
}
