package com.huyn.sampletest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val btnShare by lazy { findViewById<Button>(R.id.btn_share) }
    private val btnNext by lazy { findViewById<Button>(R.id.btn_next) }
    private val btnBack by lazy { findViewById<Button>(R.id.btn_back) }
    private val tvQuote by lazy { findViewById<TextView>(R.id.tv_quote) }
    private val tvAuthor by lazy { findViewById<TextView>(R.id.tv_author) }
    private val quoteManager by lazy { QuoteManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteManager.populateQuoteFromAssets(this, "quotes.json")
        quoteManager.getCurrentQuote().let {
            tvQuote.text = it.text
            tvAuthor.text = it.author
        }
        btnNext.setOnClickListener { _->
            quoteManager.getNextQuote().let {
                tvQuote.text = it.text
                tvAuthor.text = it.author
            }
        }
        btnBack.setOnClickListener { _->
            quoteManager.getPreviousQuote().let {
                tvQuote.text = it.text
                tvAuthor.text = it.author
            }
        }
        btnShare.setOnClickListener {
            quoteManager.getCurrentQuote().let {
                shareText(it.text)
            }
        }
    }

    private fun shareText(text: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}