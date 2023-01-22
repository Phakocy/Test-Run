package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.* ;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotNull(message = "Firstname cannot be null")
    @Pattern(regexp="^[A-Za-z-]*$", message = "Only hyphen(-) is allowed, other symbols not allowed")
    private String firstName;
    @NotNull(message = "Lastname cannot be null")
    @Pattern(regexp="^[A-Za-z-]*$", message = "Only hyphen(-) is allowed, other symbols not allowed")
    private String lastName;
    @NotNull(message = "Email cannot be null")
//    @Email (regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
//            flags = Pattern.Flag.CASE_INSENSITIVE )
    @Email
    private  String email;
}
