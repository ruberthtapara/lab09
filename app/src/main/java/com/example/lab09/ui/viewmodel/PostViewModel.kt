package com.example.lab09.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab09.data.model.PostModel
import com.example.lab09.data.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {
    private val _listaPosts = mutableStateListOf<PostModel>()
    val listaPosts: List<PostModel> get() = _listaPosts

    var post by mutableStateOf<PostModel?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun fetchPosts() {
        viewModelScope.launch {
            isLoading = true
            try {
                val posts = repository.getPosts()
                _listaPosts.clear()
                _listaPosts.addAll(posts)
            } catch (e: Exception) {
            } finally {
                isLoading = false
            }
        }
    }

    fun fetchPostById(id: Int) {
        viewModelScope.launch {
            isLoading = true
            try {
                post = repository.getPostById(id)
            } catch (e: Exception) {
            } finally {
                isLoading = false
            }
        }
    }
}
