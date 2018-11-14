package com.todcode.restmongodb.api.dto.request;

import com.todcode.restmongodb.api.dto.type.GanderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String eMail;
    private Date birth;
    private GanderType gander;
}
