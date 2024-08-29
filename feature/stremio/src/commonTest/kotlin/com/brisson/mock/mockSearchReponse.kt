package com.brisson.mock

import com.brisson.model.Meta
import com.brisson.model.SearchQueryResponse
import io.ktor.utils.io.*

val mockSearchResponseObject = SearchQueryResponse(
    query = "good",
    rank = 5.833333333333332,
    cacheMaxAge = 21600,
    metas = listOf(
        Meta(
            awards = null,
            background = null,
            behaviorHints = Meta.BehaviorHints(
                defaultVideoId = "tt0119217",
                hasScheduledVideos = false
            ),
            cast = null, // Usar null em vez de lista vazia
            country = null,
            description = null,
            director = null, // Usar null em vez de lista vazia
            genre = null, // Usar null em vez de lista vazia
            genres = null, // Usar null em vez de lista vazia
            id = "tt0119217",
            imdbRating = null,
            imdbId = "tt0119217",
            links = emptyList(),
            logo = null,
            movieDbId = null,
            name = "Good Will Hunting",
            popularities = null,
            popularity = null,
            poster = "https://m.media-amazon.com/images/M/MV5BOTI0MzcxMTYtZDVkMy00NjY1LTgyMTYtZmUxN2M3NmQ2NWJhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX250.jpg",
            releaseInfo = "1997",
            released = null,
            runtime = null,
            slug = null,
            trailerStreams = null, // Usar null em vez de lista vazia
            trailers = null, // Usar null em vez de lista vazia
            type = "movie",
            writer = null, // Usar null em vez de lista vazia
            year = null
        ),
        Meta(
            awards = null,
            background = null,
            behaviorHints = Meta.BehaviorHints(
                defaultVideoId = "tt6977338",
                hasScheduledVideos = false
            ),
            cast = null, // Usar null em vez de lista vazia
            country = null,
            description = null,
            director = null, // Usar null em vez de lista vazia
            genre = null, // Usar null em vez de lista vazia
            genres = null, // Usar null em vez de lista vazia
            id = "tt6977338",
            imdbRating = null,
            imdbId = "tt6977338",
            links = emptyList(),
            logo = null,
            movieDbId = null,
            name = "Good Boys",
            popularities = null,
            popularity = null,
            poster = "https://m.media-amazon.com/images/M/MV5BMTc1NjIzODAxMF5BMl5BanBnXkFtZTgwMTgzNzk1NzM@._V1_SX250.jpg",
            releaseInfo = "2019",
            released = null,
            runtime = null,
            slug = null,
            trailerStreams = null, // Usar null em vez de lista vazia
            trailers = null, // Usar null em vez de lista vazia
            type = "movie",
            writer = null, // Usar null em vez de lista vazia
            year = null
        )
    )
)


val mockSearchResponseJson = ByteReadChannel(
    """
    {
        "query": "good",
        "rank": 5.833333333333332,
        "cacheMaxAge": 21600,
        "metas": [
            {
                "id": "tt0119217",
                "imdb_id": "tt0119217",
                "type": "movie",
                "name": "Good Will Hunting",
                "releaseInfo": "1997",
                "poster": "https://m.media-amazon.com/images/M/MV5BOTI0MzcxMTYtZDVkMy00NjY1LTgyMTYtZmUxN2M3NmQ2NWJhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX250.jpg",
                "links": [],
                "behaviorHints": {
                    "defaultVideoId": "tt0119217",
                    "hasScheduledVideos": false
                }
            },
            {
                "id": "tt6977338",
                "imdb_id": "tt6977338",
                "type": "movie",
                "name": "Good Boys",
                "releaseInfo": "2019",
                "poster": "https://m.media-amazon.com/images/M/MV5BMTc1NjIzODAxMF5BMl5BanBnXkFtZTgwMTgzNzk1NzM@._V1_SX250.jpg",
                "links": [],
                "behaviorHints": {
                    "defaultVideoId": "tt6977338",
                    "hasScheduledVideos": false
                }
		    }
        ]
    }
    """.trimIndent()
)
