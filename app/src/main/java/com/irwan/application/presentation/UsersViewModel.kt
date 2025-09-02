package com.irwan.application.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.irwan.application.data.UsersRepository
import com.irwan.application.network.NetworkModule
import com.irwan.application.utils.UsersUiState
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class UsersViewModel(
    private val repo: UsersRepository = UsersRepository(NetworkModule.api)
) : ViewModel() {


    private val _state = kotlinx.coroutines.flow.MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val state = _state.asStateFlow()


    init { load() }


    fun load() {
        _state.value = UsersUiState.Loading
        viewModelScope.launch {
            try {
                val users = repo.fetchUsers()
                _state.value = UsersUiState.Success(users)
            } catch (t: Throwable) {
                _state.value = UsersUiState.Error(
                    t.message ?: "Terjadi kesalahan. Periksa koneksi internet Anda."
                )
            }
        }
    }


    companion object {
        val Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = UsersViewModel() as T
        }
    }
}