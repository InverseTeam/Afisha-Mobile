package ramble.sokol.sberafisha.afisha.view_model

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.model.data.AllEventsItem
import ramble.sokol.sberafisha.afisha.model.data.GetAllEventsUseCase
import ramble.sokol.sberafisha.afisha.model.data.ModelPlatform
import javax.inject.Inject

@HiltViewModel
class AllEventsViewModel @Inject constructor(
    private val getAllEventsUseCase: GetAllEventsUseCase
) : ViewModel() {

    private val _allEvents = MutableStateFlow(emptyList<AllEventsItem>())
    val allEvents: StateFlow<List<AllEventsItem>>
        get() = _allEvents

    init {
        getAllEvents()
    }

    // get events into viewModel
    private fun getAllEvents() {

        viewModelScope.launch {
            try {
                val allEvents = getAllEventsUseCase()
                _allEvents.value = allEvents
                //Log.d("MyLog", allEvents.toString())
            } catch (e: Exception){
                Log.d("MyLog", e.toString())
                _allEvents.value = listOf(AllEventsItem(-1, "error", "error", ModelPlatform("error"), "error"))
            }
        }

    }

}