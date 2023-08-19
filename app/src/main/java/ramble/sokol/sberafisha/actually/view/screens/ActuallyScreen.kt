package ramble.sokol.sberafisha.actually.view.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ActuallyScreen(
    navigator: DestinationsNavigator
){
    val context = LocalContext.current
    Toast.makeText(context, "Перейдите в другое приложение", Toast.LENGTH_SHORT).show()
}