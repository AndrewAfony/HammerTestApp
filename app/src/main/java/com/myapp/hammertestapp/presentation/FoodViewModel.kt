package com.myapp.hammertestapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.hammertestapp.domain.model.Drink
import com.myapp.hammertestapp.domain.repository.FoodRepository
import com.myapp.hammertestapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {

    var food = MutableLiveData<List<Drink>>()
    private set

    var isLoading = MutableLiveData<Boolean>()

    var eventFlow = MutableSharedFlow<UIEvent>()
    private set

    init {
        getFood()
    }

    private fun getFood() {
        viewModelScope.launch {
            repository.getListOfFood()
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            food.postValue(result.data)
                            isLoading.postValue(false)
                        }
                        is Resource.Error -> {
                            eventFlow.emit(UIEvent.ShowSnackbar(
                                message = result.message.toString()
                            ))
                            isLoading.postValue(false)
                        }
                        is Resource.Loading -> {
                            isLoading.postValue(true)
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        class ShowSnackbar(val message: String): UIEvent()
    }
}