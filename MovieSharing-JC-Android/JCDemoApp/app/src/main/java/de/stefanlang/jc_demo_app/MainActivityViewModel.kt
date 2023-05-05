package de.stefanlang.jc_demo_app

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val _count = mutableStateOf(0)
    val count: Int
        get() = _count.value

    fun onButtonClicked() {
        _count.value += 1
    }
}