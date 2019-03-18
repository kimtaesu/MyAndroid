package com.hucet.tdd.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hucet.tdd.R
import com.hucet.tdd.Sample
import com.hucet.tdd.SampleNavigation
import com.hucet.tdd.util.SampleViewModelFactory
import kotlinx.android.synthetic.main.fragment_sample.*

class SampleListFragment : Fragment() {
    companion object {
        fun newInstance(): SampleListFragment {
            return SampleListFragment()
        }
    }

    private val repository by lazy {
        SampleRepository()
    }
    var viewModelFactory: ViewModelProvider.Factory = SampleViewModelFactory(repository)

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SampleViewModel::class.java)
    }

    private val sampleClickListener: (Sample) -> Unit = {
        navigator.onDetail(it)
    }
    private val sampleAdapter by lazy {
        SampleAdapter(sampleClickListener)
    }

    lateinit var navigator: SampleNavigation

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is SampleNavigation)
            navigator = context
        else
            throw TypeCastException("$context + must implement SampleNavigation")
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