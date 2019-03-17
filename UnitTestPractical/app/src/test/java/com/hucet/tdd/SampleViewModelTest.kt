package com.hucet.tdd

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import io.kotlintest.shouldBe
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class SampleViewModelTest {
    lateinit var viewModel: SampleViewModel
    lateinit var repository: SampleRepository
    @Mock
    private lateinit var observer: Observer<List<Sample>>

    @Captor
    private lateinit var captor: ArgumentCaptor<List<Sample>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = SampleRepository()
        viewModel = SampleViewModel(repository)
    }

    @Test
    fun `empty keyword`() {
        viewModel.search("")
        viewModel.results.observeForever(observer)
        verify(observer, never()).onChanged(captor.capture())
    }

    @Test
    fun `a keyword`() {
        val expect = listOf(
            Sample("aaa"),
            Sample("abb"),
            Sample("acc")
        )

        viewModel.search("a")
        viewModel.results.observeForever(observer)
        verify(observer).onChanged(captor.capture())

        captor.value shouldBe expect
    }
}