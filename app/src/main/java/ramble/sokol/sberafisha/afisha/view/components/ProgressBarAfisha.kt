package ramble.sokol.sberafisha.afisha.view.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ramble.sokol.sberafisha.ui.theme.ColorBackgroundButton
import ramble.sokol.sberafisha.ui.theme.ColorTextSecond

@Composable
fun ProgressBarAfisha(){
    CircularProgressIndicator(
        modifier = Modifier
            .size(50.dp),
        color = ColorTextSecond
    )
}