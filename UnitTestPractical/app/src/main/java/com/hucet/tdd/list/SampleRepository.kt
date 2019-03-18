package com.hucet.tdd.list

import com.hucet.tdd.Sample

class SampleRepository {
    val fixture = listOf(
        Sample("aaa"),
        Sample("abb"),
        Sample("acc"),
        Sample("bcc")
    )

    fun search(keyword: String): List<Sample> {
        return fixture.filter {
            it.title.startsWith(keyword)
        }
    }
}