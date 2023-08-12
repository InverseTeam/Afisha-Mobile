package ramble.sokol.sberafisha.routes.domain.models

import androidx.compose.ui.res.stringResource
import ramble.sokol.sberafisha.R

class DataForTest {

    val arrQuestions = arrayOf(
        R.string.text_question_first,
        R.string.text_question_second,
        R.string.text_question_third,
        R.string.text_question_fourth)

    val arrAnswers = arrayOf(
        arrayOf("Футбол", "Баскетбол", "Бокс", "Хоккей", "Шахматы"),
        arrayOf("Сегодня", "Завтра", "Выбрать дату самостоятельно"),
        arrayOf("Да, 1-5 лет", "Да, 5-12 лет", "Да, 12-14 лет", "Да, 14-18 лет", "Нет"),
        arrayOf("Футбол", "Баскетбол", "Бокс", "Хоккей", "Шахматы"),
    )

}