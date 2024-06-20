package com.mobjoy.newsappmobjoyv1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobjoy.newsappmobjoyv1.model.ApiManager
import com.mobjoy.newsappmobjoyv1.model.articles.ArticlesItem
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    // loading - error - success
    val shouldShowLoading = MutableLiveData<Boolean>()

    //getNews
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()

    //show error dialog - screen - toast - snack-bar
    val showError = MutableLiveData<String>()

    //getNewsFromAPI
    val searchNews = MutableLiveData<List<ArticlesItem?>?>()

    fun getNewsFromAPI() { // model -> data layer
        // live data -> post value - value
        shouldShowLoading.postValue(true)
        // data API

        //view model scope
        viewModelScope.launch {
            try {
                //success
                shouldShowLoading.postValue(false)
                val response = ApiManager.getApis().getNews()
                newsLiveData.postValue(response.articles)
            } catch (e: Exception) {
                //fail
                shouldShowLoading.postValue(false)
                showError.postValue(e.localizedMessage)
            } finally {

                shouldShowLoading.postValue(false)
            }

        }

    }

    fun searchNewsFromAPI(query: String) {
        shouldShowLoading.postValue(true)
        viewModelScope.launch {
            try {

                val response = ApiManager.getApis().searchFromNews(query = query)
                searchNews.postValue(response.articles)

            } catch (e: Exception) {
                showError.postValue(e.localizedMessage)
            } finally {
                shouldShowLoading.postValue(false)
            }
        }
    }
}