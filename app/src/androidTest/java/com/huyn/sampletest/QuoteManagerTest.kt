package com.huyn.sampletest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import java.io.FileNotFoundException

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class QuoteManagerTest {

    @Test(expected = FileNotFoundException::class)
    fun populateQuoteFromAssets() {
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuoteFromAssets(context, "")
    }

    @Test(expected = JsonSyntaxException::class)
    fun populateQuoteFromAssets_InvalidJson_Expected_Exception() {
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuoteFromAssets(context, "malformed.json")
    }

    @Test( )
    fun populateQuoteFromAssets_ValidJson_Expected_count() {
        val quoteManager = QuoteManager()
        val context = ApplicationProvider.getApplicationContext<Context>()
        quoteManager.populateQuoteFromAssets(context, "quotes.json")
        Assert.assertEquals(5, quoteManager.quoteList.size)
    }

    @Test()
    fun testNextQuote_expected_CorrectQuote() {
        val quoteManager = QuoteManager()
        quoteManager.populateQuotes(arrayOf(
            Quote("This is first quote", "A"),
            Quote("This is second quote", "B"),
            Quote("This is third quote", "C")
        ))

        val quote = quoteManager.getNextQuote()
        Assert.assertEquals("B", quote.author)
    }
}