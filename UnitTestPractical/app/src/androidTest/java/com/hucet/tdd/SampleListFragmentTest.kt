package com.hucet.tdd

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.hucet.tdd.debug.SingleFragmentActivity
import com.hucet.tdd.list.SampleListFragment
import com.hucet.tdd.list.SampleViewModel
import com.hucet.tdd.test.ViewModelUtil
import com.hucet.tdd.util.DisableAnimationsRule
import com.hucet.tdd.util.RecyclerViewMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class SampleListFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    @Rule
    @JvmField
    val disableAnimationsRule = DisableAnimationsRule()

    private lateinit var viewModel: SampleViewModel
    private lateinit var fragment: SampleListFragment

    private val results = MutableLiveData<List<Sample>>()

    val fixture = listOf(
        Sample("aaa"),
        Sample("abb"),
        Sample("acc"),
        Sample("bcc")
    )


    @Before
    fun setup() {
        results.postValue(fixture)
        viewModel = mock(SampleViewModel::class.java)
        `when`(viewModel.results).then { results }
        `when`(viewModel.search(Mockito.anyString())).then { fixture }
        fragment = SampleListFragment()
        fragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(fragment)
    }

    @Test
    fun testPresentItems() {
        onView(listMatcher().atPosition(0)).check(matches(hasDescendant(withText("aaa"))))
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.sampleList)
    }
}