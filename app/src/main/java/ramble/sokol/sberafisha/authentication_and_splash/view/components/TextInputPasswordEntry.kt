@file:OptIn(ExperimentalMaterial3Api::class)

package ramble.sokol.sberafisha.authentication_and_splash.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.ui.theme.ColorBackgroundTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextHint

@Composable
fun TextInputPasswordEntry(
    text: String,
    onValueChange: (String) -> Unit,
    borderWidth: Int = 1,
    color: Color = Color.Transparent,
    interactionSource: MutableInteractionSource
){

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(width = borderWidth.dp, color = color, shape = RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp),
        value = text,
        textStyle = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(R.font.mont_semibold)),
            fontWeight = FontWeight(700),
            color = ColorTextField,
        ),
        interactionSource = interactionSource,
        onValueChange = onValueChange,
        label = {
            Text(
                stringResource(id = R.string.text_hint_password),
                style = TextStyle(
                    color = ColorTextHint,
                    fontFamily = FontFamily(Font(R.font.mont_semibold))
                )
            ) },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = ColorTextField,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            containerColor = ColorBackgroundTextField
        ),
        singleLine = true,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
}