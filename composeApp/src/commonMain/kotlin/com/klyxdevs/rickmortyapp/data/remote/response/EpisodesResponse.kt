package com.klyxdevs.rickmortyapp.data.remote.response

import com.klyxdevs.rickmortyapp.domain.model.EpisodeModel
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.*
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponse(
    val id: Int,
    val name: String,
    val episode: String,
    val characters: List<String>
) {
    fun toDomain(): EpisodeModel {
        val season = getSeasonFromEpisodeCode(episode)
        return EpisodeModel(
            id = id,
            name = name,
            episode = episode,
            characters = characters.map { url -> url.substringAfterLast("/") },
            season = season,
            videoURL = getVideoUrlFromFirebaseSeason(season)
        )
    }

    private fun getVideoUrlFromFirebaseSeason(season: SeasonEpisode): String {
        return when (season) {
            SEASON_1 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
            SEASON_2 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
            SEASON_3 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
            SEASON_4 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
            SEASON_5 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
            SEASON_6 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
            SEASON_7 -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
            UNKNOWN -> "https://firebasestorage.googleapis.com/v0/b/rickmortykmp.appspot.com/o/RICK%20Y%20MORTY%20(Trailer%20espan%CC%83ol)%20(1).mp4?alt=media&token=b7f5ef8f-aa58-4451-9d6c-b1ed83d7ba31"
        }
    }

    private fun getSeasonFromEpisodeCode(episode: String): SeasonEpisode {
        return when {
            episode.startsWith("S01") -> SEASON_1
            episode.startsWith("S02") -> SEASON_2
            episode.startsWith("S03") -> SEASON_3
            episode.startsWith("S04") -> SEASON_4
            episode.startsWith("S05") -> SEASON_5
            episode.startsWith("S06") -> SEASON_6
            episode.startsWith("S07") -> SEASON_7
            else -> UNKNOWN
        }
    }
}
