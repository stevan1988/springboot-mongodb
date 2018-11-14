package com.todcode.restmongodb.api.dto;

import com.todcode.restmongodb.api.dto.type.RoleType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "Role")
public class Role {

    @Id
    private String id;

    private RoleType type;
}
