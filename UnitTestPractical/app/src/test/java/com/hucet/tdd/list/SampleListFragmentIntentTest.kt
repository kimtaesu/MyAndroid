package com.hucet.tdd.list

import android.content.ComponentName
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import com.hucet.tdd.MainActivity
import com.hucet.tdd.Sample
import com.hucet.tdd.detail.SampleDetailActivity
import com.hucet.tdd.test.TestApplication
import io.kotlintest.shouldBe
import kotlinx.android.synthetic.main.fragment_sample.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21], application = TestApplication::class)
class SampleListFragmentIntentTest {
    lateinit var activity: MainActivity
    lateinit var shadowActivity: ShadowActivity

    @Before
    fun setUp() {
        activity = Robolectric.setupActivity(MainActivity::class.java)
        shadowActivity = shadowOf(activity)
    }

    @Test
    fun testDetailActivityIntent() {
        val testSample = Sample("aaa")

        activity.sampleList.findViewHolderForAdapterPosition(0)?.itemView?.performClick()

        val nextIntent = shadowActivity.peekNextStartedActivityForResult().intent
        nextIntent.component shouldBe ComponentName(activity, SampleDetailActivity::class.java)
        nextIntent.getParcelableExtra<Sample>(SampleDetailActivity.KEY_SAMPLE) shouldBe testSample
    }
}