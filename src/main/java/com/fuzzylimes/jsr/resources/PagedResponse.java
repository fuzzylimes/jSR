package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PagedResponse<T> {

    @JsonAlias("data")
    @JsonProperty("resourceList")
    private List<T> resourceList;

    @JsonProperty("pagination")
    private Pagination pagination;
}
