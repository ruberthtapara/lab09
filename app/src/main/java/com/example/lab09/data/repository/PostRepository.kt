package com.example.lab09.data.repository

import com.example.lab09.data.network.PostApiService
import com.example.lab09.data.model.PostModel

class PostRepository(private val apiService: PostApiService) {
    suspend fun getPosts(): List<PostModel> {
        return apiService.getUserPosts()
    }

    suspend fun getPostById(id: Int): PostModel {
        return apiService.getUserPostById(id)
    }
}
