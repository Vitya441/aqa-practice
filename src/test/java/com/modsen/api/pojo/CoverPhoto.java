package com.modsen.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CoverPhoto {

    private int id;

    private int idBook;

    private String url;
}
