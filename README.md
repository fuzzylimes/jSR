
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

### Limitations
The following set of features from the REST API are currently not supported in this Java Client
1. You can only embed a single level of embedding.
2. Currently only supports retrieving information. The creation, update, or deletion of data is not currently supported.
3. The following resources require an auth key and are not currently supported:
    1. /notifications
    2. /profile
     