package com.android.rahul.movies.network

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import self.com.githubtrendinglistapp.Constants.NEWS_API_BASE_URL
import self.com.githubtrendinglistapp.ServiceType
import self.com.githubtrendinglistapp.datamodel.Repositories
import self.com.githubtrendinglistapp.datamodel.TrendingData
import self.com.githubtrendinglistapp.network.MockServiceImpl
import self.com.githubtrendinglistapp.network.ServiceAPIHelper

class AppServiceRepo(serviceType: ServiceType):KoinComponent{
    val serviceAPIHelper: ServiceAPIHelper by inject{ parametersOf(serviceType) }
    // gets the repository list from service
    fun getRepositoriesList(onSuccess: (List<Repositories>?) -> Unit,
                     onError: (String) -> Unit){

        serviceAPIHelper.getServiceinterface()!!.fetchTrendingRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }
}


