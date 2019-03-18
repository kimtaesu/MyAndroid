package com.hucet.tdd

import com.hucet.tdd.list.SampleRepository
import io.kotlintest.shouldBe
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SampleRepositoryTest {

    private lateinit var repository: SampleRepository
    @Before
    fun setUp() {
        repository = SampleRepository()
    }

    @Test
    fun `Repository 비지니스 로직 검증`() {
        val expect = listOf(
            Sample("aaa"),
            Sample("abb"),
            Sample("acc")
        )
        Assert.assertEquals(expect, repository.search("a"))
    }
}