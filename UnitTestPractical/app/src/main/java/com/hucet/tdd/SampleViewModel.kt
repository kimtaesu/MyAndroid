package com.hucet.tdd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hucet.tdd.debug.OpenForTesting
import java.util.*

@OpenForTesting
class SampleViewModel(private val repository: SampleRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()

    val results: LiveData<List<Sample>> = Transformations
        .map(_query) { search ->
            repository.search(search)
        }


    fun search(keyword: String) {
        val input = keyword.toLowerCase(Locale.getDefault()).trim()
        if (input.isEmpty() || input == _query.value) {
            return
        }
        _query.value = input
    }
}