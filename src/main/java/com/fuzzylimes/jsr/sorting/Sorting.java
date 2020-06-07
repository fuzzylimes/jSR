package com.fuzzylimes.jsr.sorting;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sorting<T> {

    private Direction direction;
    private Class<T> orderBy;

}
