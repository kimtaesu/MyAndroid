package com.hucet.tdd.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hucet.tdd.R
import com.hucet.tdd.Sample
import com.hucet.tdd.databinding.ActivityDetailBinding

class SampleDetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_SAMPLE = "Sample"
        fun createIntent(context: Context, sample: Sample): Intent {
            return Intent(context, SampleDetailActivity::class.java).apply {
                putExtra(KEY_SAMPLE, sample)
            }
        }
    }

    private val sample by lazy {
        intent.getParcelableExtra(KEY_SAMPLE) as? Sample
    }


    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.sample = sample
    }
}