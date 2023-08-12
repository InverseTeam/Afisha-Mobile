package ramble.sokol.sberafisha.profile.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import kotlin.math.exp

@Composable
fun DropDownLanguageProfile(
    text: String,
    expanded: Boolean,
    onClick: () -> Unit,
    onDismiss: () -> Unit
){

    Box{
        Row(Modifier.clickable { // Anchor view
            onClick()
        }
        ){
            Text(text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorTextField,
                    textAlign = TextAlign.Center
                )
            )
            Icon(painter = painterResource(id = R.drawable.ic_arrowdown), contentDescription = "")
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                }) {
                Text("Popup content \nhere", Modifier.padding(24.dp))
            }
        }
    }

}