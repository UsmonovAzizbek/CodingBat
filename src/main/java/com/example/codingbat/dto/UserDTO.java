package com.example.codingbat.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    @NotNull(message = "username ustuni bo'sh bo'lishi mumkin emas")
    //@Pattern(regexp = "^[a-zA-Z0-9]{6,12}$",
         //   message = "username must be of 6 to 12 length with no special characters")
    private String username;
    @NotNull(message = "password ustuni bo'sh bo'lishi mumkin emas")
    //@Pattern(regexp = "^[a-zA-Z0-9]$",
            //message = "password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private @Size(min = 4 , max = 12) String password;
    @NotNull(message = "email ustuni bo'sh bo'lishi mumkin emas")
    private String email;
}


