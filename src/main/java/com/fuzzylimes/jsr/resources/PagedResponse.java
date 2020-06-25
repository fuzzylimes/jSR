package com.fuzzylimes.jsr.resources;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fuzzylimes.jsr.util.UnexpectedResponseException;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.fuzzylimes.jsr.clients.GenericClient.getGenericPagedResponse;

/**
 * Handles the deserialization of paginated responses. Used for most of the resources.
 * @param <T> type of response object in paginated response
 */
public class PagedResponse<T> {

    /** returned data */
    @Getter @Setter
    @JsonAlias("data")
    @JsonProperty("resourceList")
    private List<T> resourceList;

    /** pagination details */
    @Getter @Setter
    @JsonProperty("pagination")
    private Pagination pagination;

    @Setter
    private Class<T> type;

    public boolean hasNextPage() {
        return hasProperty("next");
    }

    public boolean hasPreviousPage() {
        return hasProperty("prev");
    }

    public PagedResponse<T> getNextPage() throws IOException, UnexpectedResponseException {
        return getPagedResponse(type, hasNextPage(), "next");
    }

    public PagedResponse<T> getPreviousPage() throws UnexpectedResponseException, IOException {
        return getPagedResponse(type, hasPreviousPage(), "prev");
    }

    private boolean hasProperty(String next) {
        if (pagination.getLinks().isEmpty() || pagination.getLinks() == null) {
            return false;
        } else {
            List<String> linkList = getLinkNames();
            return linkList.contains(next);
        }
    }

    private PagedResponse<T> getPagedResponse(Class<T> tClass, boolean found, String next) throws IOException, UnexpectedResponseException {
        if (found) {
            List<String> linkList = getLinkNames();
            return getGenericPagedResponse(pagination.getLinks().get(linkList.indexOf(next)).getUri(), tClass);
        } else {
            throw new UnexpectedResponseException("No pages to query");
        }
    }

    private List<String> getLinkNames() {
        return pagination.getLinks().stream().map(LinksItem::getRel).collect(Collectors.toList());
    }
}
