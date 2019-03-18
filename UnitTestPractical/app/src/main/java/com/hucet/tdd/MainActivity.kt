package com.hucet.tdd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hucet.tdd.detail.SampleDetailActivity
import com.hucet.tdd.list.SampleListFragment

interface SampleNavigation {
    fun onDetail(sample: Sample)
}

class MainActivity : AppCompatActivity(), SampleNavigation {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(android.R.id.content, SampleListFragment.newInstance())
                .commit()
    }

    override fun onDetail(sample: Sample) {
        startActivity(SampleDetailActivity.createIntent(this, sample))
    }
}
