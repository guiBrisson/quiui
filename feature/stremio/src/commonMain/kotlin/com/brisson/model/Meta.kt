package com.brisson.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchQueryResponse(
    val query: String,
    val rank: Double,
    val cacheMaxAge: Long,
    val metas: List<Meta>,
)

@Serializable
data class Metas(
    val metas: List<Meta>
)

@Serializable
data class Meta(
    val awards: String?,
    val background: String?,
    val behaviorHints: BehaviorHints,
    val cast: List<String>?,
    val country: String?,
    val description: String?,
    val director: List<String>?,
    val genre: List<String>?,
    val genres: List<String>?,
    val id: String,
    val imdbRating: String?,
    @SerialName("imdb_id") val imdbId: String,
    val links: List<Link>,
    val logo: String?,
    @SerialName("moviedb_id") val movieDbId: Int?,
    val name: String,
    val popularities: Popularities?,
    val popularity: Double?,
    val poster: String?,
    val releaseInfo: String,
    val released: String?,
    val runtime: String?,
    val slug: String?,
    val trailerStreams: List<TrailerStream>?,
    val trailers: List<Trailer>?,
    val type: String,
    val writer: List<String>?,
    val year: String?,
) {
    @Serializable
    data class BehaviorHints(
        val defaultVideoId: String?,
        val hasScheduledVideos: Boolean?,
    )

    @Serializable
    data class Link(
        val category: String,
        val name: String,
        val url: String
    )

    @Serializable
    data class Popularities(
        @SerialName("moviedb") val movieDb: Double,
        val stremio: Double,
        @SerialName("stremio_lib") val stremioLib: Int,
        val trakt: Int?,
    )

    @Serializable
    data class TrailerStream(
        val title: String,
        val ytId: String
    )

    @Serializable
    data class Trailer(
        val source: String,
        val type: String
    )
}