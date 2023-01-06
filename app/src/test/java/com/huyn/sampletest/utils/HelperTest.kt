package com.huyn.sampletest.utils

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class HelperTest {

    lateinit var helper: Helper

    @Before
    fun setUp() {
        helper = Helper()
    }

    @Test
    fun isPallindrome() {
        val result = helper.isPallindrome("hello")
        assertEquals(false, result)
    }

    @Test
    fun isPallindrome_inputString_level_expectedTrue() {
        val result = helper.isPallindrome("level")
        assertEquals(true, result)
    }

}