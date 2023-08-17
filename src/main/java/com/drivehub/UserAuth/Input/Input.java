package com.drivehub.UserAuth.Input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Input {
    public String getEmail() {
        if(email.contains("@") == true){
            return email;
        }throw new Error("Email is invalid");
    }

    private String email;

    private String password;
}
