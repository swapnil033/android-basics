package com.swapnil.nested_navigation_app.presentation.featureAuth.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _name = mutableStateOf<String>("")
    val name: State<String> = _name

    fun setName(name: String){
        _name.value = name
    }

}