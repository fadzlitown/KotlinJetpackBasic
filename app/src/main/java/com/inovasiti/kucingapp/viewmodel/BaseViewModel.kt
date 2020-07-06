package com.inovasiti.kucingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

//AndroidViewModel = required appContext bcs we will need to access the DB, cannot use ViewModel = required context & can be destroy
abstract class BaseViewModel (app: Application) : AndroidViewModel(app), CoroutineScope{
    //Run db job in the Background thread
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}