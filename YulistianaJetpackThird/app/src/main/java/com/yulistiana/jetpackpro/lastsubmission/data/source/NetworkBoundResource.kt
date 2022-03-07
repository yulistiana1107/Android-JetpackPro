package com.yulistiana.jetpackpro.lastsubmission.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.vo.ApiResponses
import com.yulistiana.jetpackpro.lastsubmission.data.source.remote.vo.StatusResponses
import com.yulistiana.jetpackpro.lastsubmission.vo.Resources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val outcome = MediatorLiveData<Resources<ResultType>>()

    init {
        outcome.value = Resources.loading(null)

        @Suppress("LeakingThis")
        val favoriteDbSource = loadFromDB()

        outcome.addSource(favoriteDbSource) { data ->
            outcome.removeSource(favoriteDbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(favoriteDbSource)
            } else {
                outcome.addSource(favoriteDbSource) { newData ->
                    outcome.value = Resources.success(newData)
                }
            }
        }
    }

    private fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponses<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiRevenge = createCall()

        outcome.addSource(dbSource) { newData ->
            outcome.value = Resources.loading(newData)
        }
        outcome.addSource(apiRevenge) { revenge ->
            outcome.removeSource(apiRevenge)
            outcome.removeSource(dbSource)
            when (revenge.status) {
                StatusResponses.SUCCESS ->
                    CoroutineScope(IO).launch {
                        revenge.body?.let { saveCallResult(it) }
                        Log.d("BOUND 1 : ", revenge.status.name)

                        withContext(Main) {
                            outcome.addSource(loadFromDB()) { newData ->
                                outcome.value = Resources.success(newData)
                            }
                        }

                    }

                StatusResponses.ERROR -> {
                    onFetchFailed()
                    Log.d("BOUND 2 : ", revenge.status.name)
                    outcome.addSource(dbSource) { newData ->
                        outcome.value = Resources.error(revenge.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resources<ResultType>> = outcome
}