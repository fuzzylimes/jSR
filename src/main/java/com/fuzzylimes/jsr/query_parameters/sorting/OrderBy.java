package com.fuzzylimes.jsr.query_parameters.sorting;

/**
 * Specifies an object that can be used to define the sorting order for a resource
 */
public interface OrderBy {

    /**
     * @return The value to be ordered by
     */
    String getOrderBy();

}
