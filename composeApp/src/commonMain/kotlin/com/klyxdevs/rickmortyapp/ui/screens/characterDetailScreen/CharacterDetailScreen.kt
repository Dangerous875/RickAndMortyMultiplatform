package com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import com.klyxdevs.rickmortyapp.domain.model.EpisodeModel
import com.klyxdevs.rickmortyapp.isDesktop
import com.klyxdevs.rickmortyapp.ui.components.CircularProgressBar
import com.klyxdevs.rickmortyapp.ui.components.TextTitle
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundPrimaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundSecondaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundTertiaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.DefaultTextColor
import com.klyxdevs.rickmortyapp.ui.core.colors.Pink
import com.klyxdevs.rickmortyapp.ui.core.extensions.aliveBorder
import com.klyxdevs.rickmortyapp.ui.core.extensions.decodingObject
import com.klyxdevs.rickmortyapp.ui.core.navigation.HomeScreenRoute
import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.model.CharacterDetail
import com.klyxdevs.rickmortyapp.ui.screens.characterDetailScreen.viewmodel.CharacterDetailViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.ic_back
import rickmortyapp.composeapp.generated.resources.space

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharacterDetailScreen(characterDetail: String, mainNavHostController: NavHostController) {
    val character = characterDetail.decodingObject()
    logs(characterDetail, character)
    val viewModel =
        koinViewModel<CharacterDetailViewModel>(parameters = { parametersOf(character) })
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)
            .verticalScroll(scrollState)
    ) {
        MainHeader(uiState.characterDetail) {
            mainNavHostController.navigate(HomeScreenRoute) {
                popUpTo(HomeScreenRoute) { inclusive = true }
            }
        }

        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxSize()
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)).background(
                    BackgroundSecondaryColor
                )
        ) {
            CharacterInformation(uiState.characterDetail)
            CharacterEpisodesList(uiState.episodes)
        }

    }

}

@Composable
fun CharacterEpisodesList(episodes: List<EpisodeModel>?) {
    ElevatedCard(
        modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors().copy(containerColor = BackgroundTertiaryColor)
    ) {
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            if (episodes == null) {
                CircularProgressBar(color = Green)
            } else {
                Column {
                    TextTitle("Episode list")
                    Spacer(Modifier.height(6.dp))

                    episodes.forEach { episode ->
                        EpisodeItem(episode)
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun EpisodeItem(episode: EpisodeModel) {
    Text(episode.name, color = Green, fontWeight = FontWeight.Bold)
    Text(episode.episode, color = DefaultTextColor)
}

@Composable
fun CharacterInformation(characterDetail: CharacterDetail) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors().copy(containerColor = BackgroundTertiaryColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextTitle("About the character")
            Spacer(Modifier.height(6.dp))
            InformationDetail("Origin: ", characterDetail.origin)
            Spacer(Modifier.height(2.dp))
            InformationDetail("Gender: ", characterDetail.gender)
        }
    }
}

@Composable
fun InformationDetail(title: String, detail: String) {
    Row {
        Text(title, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        Spacer(Modifier.width(4.dp))
        Text(detail, color = Green)
    }
}

@Composable
fun MainHeader(characterDetail: CharacterDetail, onBackPressed: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        Image(
            painter = painterResource(Res.drawable.space),
            "Background header",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        if (isDesktop()) {
            Icon(
                painter = painterResource(Res.drawable.ic_back),
                null,
                tint = Color.White,
                modifier = Modifier.padding(16.dp).size(24.dp).clickable { onBackPressed() }
            )
        }
        CharacterHeader(characterDetail)
    }
}

@Composable
fun CharacterHeader(characterDetail: CharacterDetail) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier.fillMaxWidth().height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                characterDetail.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text("Species: ${characterDetail.species}", color = Color.Black)
        }

        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier.size(205.dp).clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterDetail.image,
                        contentDescription = null,
                        modifier = Modifier.size(190.dp).clip(CircleShape)
                            .aliveBorder(characterDetail.isAlive),
                        contentScale = ContentScale.Crop
                    )
                }

                val aliveCopy = if (characterDetail.isAlive) "ALIVE" else "DEAD"
                val aliveColor = if (characterDetail.isAlive) Green else Color.Red
                Text(
                    aliveCopy,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clip(RoundedCornerShape(30)).background(aliveColor)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}

private fun logs(
    characterDetail: String,
    character: CharacterDetail
) {
    Logger.i("DecoderFunction -> $characterDetail")
    Logger.i("DecoderFunction -> $character")
}