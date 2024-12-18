package com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import app.cash.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import com.klyxdevs.rickmortyapp.ui.components.CircularProgressBar
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundPrimaryColor
import com.klyxdevs.rickmortyapp.ui.core.colors.DefaultTextColor
import com.klyxdevs.rickmortyapp.ui.core.extensions.vertical
import com.klyxdevs.rickmortyapp.ui.core.navigation.CharacterDetailRoute
import com.klyxdevs.rickmortyapp.ui.screens.homeScreen.tabs.characters.viewmodel.CharactersViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import rickmortyapp.composeapp.generated.resources.Res
import rickmortyapp.composeapp.generated.resources.rickface

@OptIn(KoinExperimentalAPI::class)
@Composable
fun CharactersScreen(mainNavHostController: NavHostController) {
    val charactersViewModel = koinViewModel<CharactersViewModel>()
    val state by charactersViewModel.state.collectAsState()
    val characters: LazyPagingItems<CharacterModel> = state.characters.collectAsLazyPagingItems()
    CharactersGridList(state, characters) { characterModel ->
        mainNavHostController.navigate(
            CharacterDetailRoute(
                characterModel.toCharacterDetail().encodingObject()
            )
        )
    }
}

@Composable
fun CharactersGridList(
    state: CharacterState,
    characters: LazyPagingItems<CharacterModel>,
    onSelectItem: (CharacterModel) -> Unit
) {
    Column {
        Text(
            "Characters",
            color = DefaultTextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp).background(BackgroundPrimaryColor), textAlign = TextAlign.Center
        )
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor).padding(horizontal = 16.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                CharacterOfTheDay(state.characterOfTheDay){onSelectItem(it)}
            }
            when {
                characters.loadState.refresh is LoadState.Loading && characters.itemCount == 0 -> {
                    //carga inicial
                    item(span = { GridItemSpan(2) }) {
                        CircularProgressBar(color = Green, size = 60.dp)
                    }
                }

                characters.loadState.refresh is LoadState.NotLoading && characters.itemCount == 0 -> {
                    // API vacia
                    item(span = { GridItemSpan(2) }) {
                        Text("Empty list :(")
                    }
                }

                else -> {
                    // Recorremos los items
                    items(characters.itemCount) { pos ->
                        characters[pos]?.let { characterModel ->
                            CharacterItemList(characterModel) { onSelectItem(it) }
                        }
                    }
                    // carga final
                    if (characters.loadState.append is LoadState.Loading) {
                        item(span = { GridItemSpan(2) }) {
                            CircularProgressBar(color = Green)

                        }
                    }
                }


            }
        }
    }

}

@Composable
fun CharacterItemList(characterModel: CharacterModel, onSelectItem: (CharacterModel) -> Unit) {
    Box(
        modifier = Modifier.clip(RoundedCornerShape(24))
            .border(2.dp, Green, shape = RoundedCornerShape(0, 24, 0, 24)).fillMaxWidth()
            .height(150.dp)
            .clickable { onSelectItem(characterModel) },
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = characterModel.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            placeholder = painterResource(Res.drawable.rickface)
        )

        Box(
            modifier = Modifier.fillMaxWidth().height(60.dp).background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Black.copy(0f),
                        Color.Black.copy(0.6f),
                        Color.Black.copy(1f)
                    )
                )
            ), contentAlignment = Alignment.Center
        ) {
            Text(characterModel.name, color = Color.White, fontSize = 18.sp)
        }
    }

}


@Composable
fun CharacterOfTheDay(characterModel: CharacterModel? = null , onSelectItem: (CharacterModel) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().height(400.dp).padding(top = 4.dp).clickable { onSelectItem(characterModel!!) }, shape = RoundedCornerShape(12)
    ) {
        if (characterModel == null) {
            CircularProgressBar(color = Green)
        } else {
            Box(contentAlignment = Alignment.BottomStart) {
                AsyncImage(
                    model = characterModel.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    Modifier.fillMaxSize().background(
                        Brush.horizontalGradient(
                            0f to Color.Black.copy(alpha = 0.9f),
                            0.4f to Color.White.copy(alpha = 0f)
                        )
                    )
                )
                Text(
                    characterModel.name,
                    fontSize = 40.sp,
                    maxLines = 1,
                    minLines = 1,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                        .fillMaxHeight().vertical().rotate(-90f)
                )
            }
        }
    }
}
