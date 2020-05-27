package com.fuzzylimes.jSR.clients;

public class UserClient {

    // GET users
    // Returns a list of User objects
    // Supports the query parameters identified in `UserQuery`
    // Supports sorting using OrderBy and `UserOrderBy`
    // Supports sort direction using Direction
    // https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-users


    // GET users/{id}
    // Returns a single User object
    // https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-usersid


    // GET users/{id}/personal-bests
    // Returns a list of Runs.
    // This will not include obsolete runs.
    // Supports the query parameters identified in `UserPersonalBestsQuery`
    // Supports same embed options as Runs: game,category,level,players,region,platform
    // https://github.com/speedruncomorg/api/blob/master/version1/users.md#get-usersidpersonal-bests


}
