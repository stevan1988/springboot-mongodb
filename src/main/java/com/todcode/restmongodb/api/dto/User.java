package com.todcode.restmongodb.api.dto;

import com.todcode.restmongodb.api.dto.type.GanderType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "User")
public class User {

    @Id
    private String id;

    @NotBlank
    @Size(max = 32)
    private String firstName;

    @NotBlank
    @Size(max = 32)
    private String lastName;

    @NotBlank
    @Size(max = 32)
    private String email;

    @NotNull
    private Date birthDate;

    private GanderType ganderType;

    @NotBlank
    @Size(max = 16)
    private String username;

    @NotBlank
    @Size(max = 128)
    private String password;

    @DBRef
    private Set<Role> roles = new HashSet<Role>();
}
