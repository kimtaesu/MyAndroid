package com.hucet.tdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_sample.*

class SampleViewModelFactory(val repository: SampleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(SampleRepository::class.java).newInstance(repository)
    }
}

class SampleListFragment : Fragment() {
    companion object {
        fun newInstance(): SampleListFragment {
            return SampleListFragment()
        }
    }

    private val repository by lazy {
        SampleRepository()
    }
    private val factory by lazy {
        SampleViewModelFactory(repository)
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(SampleViewModel::class.java)
    }
    private val sampleAdapter by lazy {
        SampleAdapter {}
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_sample, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.results.observe(this, Observer {
            sampleAdapter.submitList(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sampleList.apply {
            adapter = sampleAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.search("A")
    }
}