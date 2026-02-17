package com.modsen.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Book {

    private int id;

    private String title;

    private String description;

    private int pageCount;

    private String excerpt;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime publishDate;
}
