package com.api.Films.DTO;


import com.api.Films.entity.enums.TypePersonEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String name;
    private String surname;
    private TypePersonEnum type;
}
