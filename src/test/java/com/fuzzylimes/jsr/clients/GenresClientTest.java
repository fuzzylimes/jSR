package com.fuzzylimes.jsr.clients;

import com.fuzzylimes.jsr.query_parameters.sorting.Direction;
import com.fuzzylimes.jsr.query_parameters.sorting.GenresOrderBy;
import com.fuzzylimes.jsr.query_parameters.sorting.Sorting;
import com.fuzzylimes.jsr.resources.Genre;
import com.fuzzylimes.jsr.resources.PagedResponse;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrl;
import static com.fuzzylimes.jsr.JacksonTests.util.ClientTestUtil.MockJsrClientUrlAndQueryParams;

class GenresClientTest {

    @Test
    void getGenres_SortingTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Genres.json");

        Sorting<GenresOrderBy> order = Sorting.<GenresOrderBy>builder()
                .direction(Direction.ASCCENDING).orderBy(GenresOrderBy.NAME).build();
        PagedResponse<Genre> var = GenresClient.getGenres(order);
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("d5ogj82w", var.getResourceList().get(3).getId());
    }

    @Test
    void getGenresTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrlAndQueryParams("/responses/Genres.json");

        PagedResponse<Genre> var = GenresClient.getGenres();
        Assertions.assertEquals(20, var.getPagination().getSize());
        Assertions.assertEquals("d5ogj82w", var.getResourceList().get(3).getId());
    }

    @Test
    void getGenresByIdTest() throws IOException, UnexpectedResponseException {
        MockJsrClientUrl("/responses/GenresById.json");

        Genre var = GenresClient.getGenresById("r2lwz857");
        Assertions.assertEquals(2, var.getLinks().size());
        Assertions.assertEquals("r2lwz857", var.getId());
    }
}