package com.nanaten.retrofitcoroutine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaten.retrofitcoroutine.api.ApiService
import com.nanaten.retrofitcoroutine.api.GithubApi
import com.nanaten.retrofitcoroutine.entity.Repos
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainViewModel : ViewModel() {
    private val api = ApiService.get()
    val data = MutableLiveData<Repos>()

    fun getRepos() {
        api.getRepos().enqueue(object : Callback<Repos> {
            override fun onResponse(call: Call<Repos>, response: Response<Repos>) {
                data.postValue(response.body())
            }

            override fun onFailure(call: Call<Repos>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getReposWithCoroutine() {
        viewModelScope.launch {
            try {
                val repos = api.getReposWithCoroutine()
                data.postValue(repos)
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}