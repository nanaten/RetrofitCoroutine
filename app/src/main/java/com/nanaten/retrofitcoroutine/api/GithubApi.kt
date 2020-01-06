package com.nanaten.retrofitcoroutine.api

import com.nanaten.retrofitcoroutine.entity.Repos
import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {
    @GET("/repos/octocat/Hello-World")
    suspend fun getReposWithCoroutine(): Repos

    @GET("/repos/octocat/Hello-World")
    fun getRepos(): Call<Repos>
}