package preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.klyxdevs.rickmortyapp.domain.model.CharacterModel
import com.klyxdevs.rickmortyapp.ui.homeScreen.tabs.characters.CharacterOfTheDay

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_3_XL
)
@Composable
fun PreviewScreen() {
    CharacterOfTheDay(CharacterModel(id = "22", isAlive = true, name = "pepeppepepepepepeep", image = ""))
}