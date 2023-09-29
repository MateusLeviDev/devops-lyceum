package nvoip.api.requests;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "Numbersip cannot be null")
    private String numbersip;
    private String userToken;
    private String telephone;
}
