package com.fuzzylimes.jSR.clients;

public class LevelsClient {

    // GET levels/{id}
    // Returns a single {@link Level} object
    // Supports embed options: categories,variables
    // https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsid



    // GET levels/{id}/categories
    // Returns a list of {@list Category} for the level
    // Supports the query parameters identified in {@link LevelCategoriesQuery}
    // Supports Order by and Direction in {@link CategoriesOrderBy} and {@link Direction}
    // https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidcategories


    // GET levels/{id}/variables
    // Returns a list of {@link Variable}
    // https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidvariables


    // GET levels/{id}/records
    // Returns a {@link PagedResponse} of {@link Record} objects
    // https://github.com/speedruncomorg/api/blob/master/version1/levels.md#get-levelsidrecords

}
