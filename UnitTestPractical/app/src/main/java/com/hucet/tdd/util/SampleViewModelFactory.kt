package com.hucet.tdd.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hucet.tdd.list.SampleRepository

class SampleViewModelFactory(val repository: SampleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SampleRepository::class.java).newInstance(repository)
    }
}