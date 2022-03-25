package com.diegolima.mebooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.diegolima.mebooks.R
import com.diegolima.mebooks.domain.models.Book

class DetailsActivity : AppCompatActivity() {
    private lateinit var titulo: TextView
    private var book: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        book = intent.getParcelableExtra("book")
        titulo = findViewById(R.id.tvTitleDetails)
        titulo.text = book?.titulo
    }
}