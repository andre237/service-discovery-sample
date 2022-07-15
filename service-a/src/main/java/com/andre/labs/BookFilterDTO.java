package com.andre.labs;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BookFilterDTO {

    private String name;
    private Integer pageCount;
    private String author;

}
