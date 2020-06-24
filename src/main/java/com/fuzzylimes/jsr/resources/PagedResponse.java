package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Handles the deserialization of paginated responses. Used for most of the resources.
 * @param <T> type of response object in paginated response
 */
@Data
public class PagedResponse<T> {

    /** returned data */
    @JsonAlias("data")
    @JsonProperty("resourceList")
    private List<T> resourceList;

    /** pagination details */
    @JsonProperty("pagination")
    private Pagination pagination;
}
