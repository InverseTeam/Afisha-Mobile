import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DropDownLanguageProfile() {
    // Список элементов выпадающего списка
    val items = listOf("Item 1", "Item 2", "Item 3")

    // Состояние для выбора элемента
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(items[0]) }

    // Состояние для анимации
    val textColor by animateColorAsState(
        if (expanded) Color.White else Color.Black,
        TweenSpec(300)
    )
    val backgroundColor by animateColorAsState(
        if (expanded) Color.Blue else Color.LightGray,
        TweenSpec(300)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedOption,
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )

            if (expanded) {
                Text(
                    text = "▲",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            } else {
                Text(
                    text = "▼",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        if (expanded) {
            items.forEach { item ->
                Text(
                    text = item,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedOption = item
                            expanded = false
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .background(color = Color.LightGray)
                        .wrapContentHeight()
                )
            }
        }
    }
}