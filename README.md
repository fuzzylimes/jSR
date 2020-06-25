## Overview
jSR (javaSpeedRun) is a Java client for the public SpeedRun.com APIs. It simplifies the process 
of retrieving data using their rest APIs by abstracting away the calls, and serializing all response
data into pojos. I wanted to provide something that offered full object mapping for all responses
rather than just providing the generic, raw, JSON paylaods. The API can be a bit difficult
to work with, as responses can very wildly depending certain query params. Rather than put this onus
on the user, I wanted to handle this all for them.

The library provides a set of static clients that correspond to each of the resources on the
speedrun.com REST APIs. Each client uses an OkHttp connection to handle requests to the speedrun.com
backend and return the parsed data. Error handling included.

This is my first attempt at writing a library, and wanted to give something back to the speed running
community. I hope that someone can find this useful for their project.

## Documentation
The code is completely documented and is your best resource. If behavior is not clear from the javadocs,
please open a ticket.

General functionality and usage will be covered in this readme.

## Usage
A corresponding client exists for each of the SpeedRun.com resources. The clients are:

1. CategoryClient
2. GameClient
3. GuestClient
4. LeaderboardsClient
5. LevelsClient
6. PlatformClient
7. RegionClient
8. RunsClient
9. SeriesClient
10. UserClient
11. VariableClient

Each of these clients contain all of the possible queries that can be made for their respective resource.
Queries fall into one of a few different categories:
1. Basic Query
2. Query using additional query params
3. Query using sorting parameters
4. Query with additional embedded values
5. Some combination of the above.

Support for these queries will vary from API to API. When supported, you will see that multiple instances
of a method exist that support all variations

### Error Handling
If the client is unable to get an expected (2xx) response back from the speedrun.com server, an
`UnexpectedResponseException` will be thrown. It will provide details as to why the request failed
(i.e. whether or not there quest returned no data, timed out, or returned invalid data). You should
plan to handle this exception, as all clients will throw it.

### Examples
The following are a set of examples to show how to use the Library in your project.
#### Basic usage
The general flow for making a request is going to be `<clientName>.get<resourceName>(<parameters>)`.

For example, let's say we want to retrieve a list of all of the supported game Engines that are documented in
speedrun.coms database. To do so, we'd simply use:
```java
PagedResponse<Engine> var = EnginesClient.getEngines();
```
This returns back a paged response of game engines that we can work with.

#### Embedded Objects
Many of the speedrun.com APIs offer the ability to embed related resources into the response message. By default,
jSR will return ALL of the supported embed options when the `embed` flag is set to true.

For instance, let's say we want to query for a user's PBs, and we'd like to get the full game object back in the response
so that we can grab some additional data from it. Our request would be:
```java
List<PersonalBest> var = UserClient.getRunsForUser("o86vmv3j", true);
```
This returns a list of PB records for the user, replaced with embedding where possible.

For a list of what properties can be embedded for which resources, see [the Properties file](src/main/java/com/fuzzylimes/jsr/common/Properties.java).

#### QueryParams/Sorting
Many resources support the ability to include query parameters or specify the way in which the response should be sorted.
This is handled through the use of builders.

For example, let's say that we want to get the user resource for a user, but we only know what their name is on Twitch.
We can set up a `UserQuery` and then specify that we want to sort that response using the `UserOrderBy` class. This would
look something like:
```java
UserQuery query = UserQuery.builder().twitch("Elajjaz").build();
Sorting<UsersOrderBy> order = Sorting.<UsersOrderBy>builder().direction(Direction.ASCCENDING).build();
PagedResponse<User> var = UserClient.getUsers(query, order);
``` 

#### Additional Examples
You can see some additional examples in the test repo:
* [General UT](src/test/java/com/fuzzylimes/jsr/clients)
* [Integration Tests](src/test/java/com/fuzzylimes/jsr/clients/integration_tests)

### Limitations
The following set of features from the REST API are currently not supported in this Java Client
1. You can only embed a single level of embedding.
2. Currently only supports retrieving information. The creation, update, or deletion of data is not currently supported.
3. The following resources require an auth key and are not currently supported:
    1. /notifications
    2. /profile

## Contributing
Any bug reports or enhancement requests are welcome! At the moment I'm not looking for any assistance
with maintaining the project, but I would love feedback from users. Please feel free to open any
issues you may come across.

## Licensing
Standard MIT license applies.