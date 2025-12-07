package org.example.project.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.project.domain.model.CharacterModel
import org.example.project.domain.model.EpisodeModel
import org.example.project.ui.core.BackgroundPrimaryColor
import org.example.project.ui.core.BackgroundSecondaryColor
import org.example.project.ui.core.BackgroundTertiaryColor
import org.example.project.ui.core.DefaultTextColor
import org.example.project.ui.core.Green
import org.example.project.ui.core.Pink
import org.example.project.ui.core.components.TextTitle
import org.example.project.ui.core.ex.aliveBorder
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import rickmorty.composeapp.generated.resources.Res
import rickmorty.composeapp.generated.resources.space

@Composable
fun CharacterDetailScreen(characterModel: CharacterModel) {

    val characterDetailViewModel =
        koinViewModel<CharacterDetailViewModel>(parameters = { parametersOf(characterModel) })

    val state by characterDetailViewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor).padding(it)
                .verticalScroll(scrollState)
        ) {
            MainHeader(state.characterModel)
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxSize()
                    .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10)).background(
                        BackgroundSecondaryColor
                    )
            ) {
                CharacterInformation(state.characterModel)
                CharacterEpisodesList(state.episodes)
            }
        }
    }
}


@Composable
fun MainHeader(characterModel: CharacterModel) {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
        Image(
            painter = painterResource(Res.drawable.space),
            contentDescription = "image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CharacterHeader(characterModel)
    }
}

@Composable
fun CharacterHeader(characterModel: CharacterModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(topStartPercent = 10, topEndPercent = 10))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = characterModel.name,
                color = Pink,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = "Especie: ${characterModel.species}", color = Color.Black)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(contentAlignment = Alignment.TopCenter) {
                Box(
                    modifier = Modifier.size(205.dp).clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = characterModel.image,
                        contentDescription = "imagen",
                        modifier = Modifier.size(190.dp).clip(CircleShape)
                            .aliveBorder(characterModel.isAlive),
                        contentScale = ContentScale.Crop
                    )
                }

                val alive = if (characterModel.isAlive) "Alive" else "Dead"
                val color = if (characterModel.isAlive) Green else Color.Red
                Text(
                    text = alive,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clip(RoundedCornerShape(30)).background(color)
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CharacterInformation(characterModel: CharacterModel) {
    ElevatedCard(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors().copy(containerColor = BackgroundTertiaryColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextTitle("About the character")
            Spacer(modifier = Modifier.height(6.dp))
            InformationDetail("Origin: ", characterModel.origin)
            Spacer(modifier = Modifier.height(2.dp))
            InformationDetail("Gender: ", characterModel.gender)
        }
    }
}

@Composable
fun InformationDetail(title: String, detail: String) {
    Row {
        Text(title, color = DefaultTextColor, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(4.dp))
        Text(detail, color = Green)
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
                CircularProgressIndicator(color = Green)
            } else {
                Column {
                    TextTitle("Episode List")
                    Spacer(modifier = Modifier.height(6.dp))

                    episodes.forEach { episode ->
                        EpisodeItem(episode)
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }

    }

}

@Composable
fun EpisodeItem(episode: EpisodeModel) {
    Text(episode.name, color =Green, fontWeight = FontWeight.Bold)
    Text(episode.episode, color = DefaultTextColor)
}