package com.modsen.api.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

    private int id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime dueDate;

    private boolean completed;
}
