package com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.episodes

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import com.klyxdevs.rickmortyapp.domain.model.EpisodeModel
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.SEASON_1
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.SEASON_2
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.SEASON_3
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.SEASON_4
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.SEASON_5
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.SEASON_6
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.SEASON_7
import com.klyxdevs.rickmortyapp.domain.model.SeasonEpisode.UNKNOWN
import com.klyxdevs.rickmortyapp.ui.components.CircularProgressBar
import com.klyxdevs.rickmortyapp.ui.components.PagingType
import com.klyxdevs.rickmortyapp.ui.components.PagingWrapper
import com.klyxdevs.rickmortyapp.ui.components.VideoPlayer
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundPrimaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.DefaultTextColor
import com.klyxdevs.rickmortyapp.ui.core.colors.PlaceholderColor
import com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.episodes.viewmodel.EpisodesViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.placeholder
import rickmortyapp.composeapp.generated.resources.portal
import rickmortyapp.composeapp.generated.resources.season1
import rickmortyapp.composeapp.generated.resources.season2
import rickmortyapp.composeapp.generated.resources.season3
import rickmortyapp.composeapp.generated.resources.season4
import rickmortyapp.composeapp.generated.resources.season5
import rickmortyapp.composeapp.generated.resources.season6
import rickmortyapp.composeapp.generated.resources.season7

@OptIn(KoinExperimentalAPI::class)
@Composable
fun EpisodesScreen() {
    val episodesViewModel = koinViewModel<EpisodesViewModel>()

    val state by episodesViewModel.state.collectAsState()
    val episodes = state.characters.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)) {
        Spacer(modifier = Modifier.height(16.dp))
        PagingWrapper(
            pagingType = PagingType.ROW,
            pagingItems = episodes,
            initialView = { CircularProgressBar(color = Color.Green.copy(alpha = 0.5f)) },
            itemView = { EpisodesItemList(it) { url -> episodesViewModel.onPlaySelected(url) } })

        EpisodePlayer(state) { episodesViewModel.onCloseVideo() }
    }


}

@Composable
private fun EpisodePlayer(state: EpisodesState, onCloseVideo: () -> Unit) {
    AnimatedContent(state.playVideo.isNotBlank()) { condition ->
        if (condition){
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().height(250.dp).padding(horizontal = 16.dp, vertical = 24.dp)
                    .border(3.dp, Color.Green, CardDefaults.elevatedShape)
            ) {
                Box(modifier = Modifier.background(Color.Black)) {
                    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
                        VideoPlayer(Modifier.fillMaxWidth().height(400.dp), state.playVideo)
                    }
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(Res.drawable.portal),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(40.dp)
                                .clickable { onCloseVideo() }
                        )
                    }
                }
            }
        }else{
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.elevatedCardColors().copy(containerColor = PlaceholderColor)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(Res.drawable.placeholder), null)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Aw, jeez, you gotta click the video, guys! I mean, it might be important or something!",
                        color = DefaultTextColor,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.padding(16.dp)

                    )
                }
            }
        }
    }
}

@Composable
fun EpisodesItemList(episodes: EpisodeModel, onEpisodeSelected: (String) -> Unit) {
    Column(
        modifier = Modifier.width(120.dp).padding(horizontal = 8.dp)
            .clickable { onEpisodeSelected(episodes.videoURL) }) {
        Image(
            modifier = Modifier.height(180.dp).fillMaxWidth(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            painter = painterResource(
                getSeasonImage(episodes.season)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.padding(horizontal = 8.dp).background(BackgroundPrimaryColor).fillMaxWidth() .border(2.dp, Green, shape = RoundedCornerShape(0, 24, 0, 24)), contentAlignment = Alignment.Center){
            Text(text = episodes.episode, color = DefaultTextColor)
        }
    }
}

//fun getSeasonName(name: String): String {
//    val season = name.substringBefore("_")
//    val number = name.substringAfterLast("_")
//    return "$season: $number"
//}

fun getSeasonImage(seasonEpisode: SeasonEpisode): DrawableResource {
    return when (seasonEpisode) {
        SEASON_1 -> Res.drawable.season1
        SEASON_2 -> Res.drawable.season2
        SEASON_3 -> Res.drawable.season3
        SEASON_4 -> Res.drawable.season4
        SEASON_5 -> Res.drawable.season5
        SEASON_6 -> Res.drawable.season6
        SEASON_7 -> Res.drawable.season7
        UNKNOWN -> Res.drawable.season1
    }
}

