package com.hucet.tdd

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import com.hucet.tdd.list.SampleRepository
import org.junit.Assert
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
@Config(sdk = [21])
class SampleRepositoryTest {

    lateinit var activity: MainActivity
    lateinit var activityController: ActivityController<MainActivity>
    lateinit var shadowActivity: ShadowActivity
    @Before
    fun setUp() {
        val intent = Intent(ApplicationProvider.getApplicationContext(), MainActivity::class.java)
        activity = Robolectric.buildActivity(MainActivity::class.java, intent).start().visible().get()
        activityController = Robolectric.buildActivity(MainActivity::class.java)
        shadowActivity = shadowOf(activity)
    }

    @Test
    fun testDetailIntent() {
        val nextIntent = shadowActivity.peekNextStartedActivityForResult().intent
    }
}