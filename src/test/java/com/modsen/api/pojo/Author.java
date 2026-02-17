package com.modsen.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private int id;

    private int idBook;

    private String firstName;

    private String lastName;
}
