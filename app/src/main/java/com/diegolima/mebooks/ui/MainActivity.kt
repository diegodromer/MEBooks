package com.diegolima.mebooks.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.mebooks.R
import com.diegolima.mebooks.domain.models.Book
import com.diegolima.mebooks.ui.adapter.EditorialBookViewAdapter
import com.diegolima.mebooks.ui.viewmodel.BookViewModel

class MainActivity : AppCompatActivity(), ClickListener {
    private lateinit var bookRecyclerView: RecyclerView
    private var editorialBookViewAdapter: EditorialBookViewAdapter? = null
    private lateinit var edtIsbnBook: EditText
    private lateinit var btnSearchBook: Button
    private lateinit var tvResultBook: TextView
    private var viewModel: BookViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtIsbnBook = findViewById(R.id.edtIsbnBook)
        btnSearchBook = findViewById(R.id.btnSearchBook)
        tvResultBook = findViewById(R.id.tvResultBook)
        bookRecyclerView = findViewById(R.id.recyclerView)
        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)

        showKeyboard()
        initRecyclerView()
        initViewModel()
        initSearch()
    }

    private fun initRecyclerView() {
        bookRecyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            editorialBookViewAdapter = EditorialBookViewAdapter(this)
            it.adapter = editorialBookViewAdapter
        }
    }

    override fun onItemClick(view: View, book: Book?) {
        intent = Intent(this@MainActivity, DetailsActivity::class.java).apply {
            putExtra("book", book)
        }
        startActivity(intent)
    }

    private fun initViewModel() {
        viewModel?.getLiveDataObserver()?.observe(this, Observer {
            if (it != null) {
                editorialBookViewAdapter?.setBooksList(it)
                editorialBookViewAdapter?.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel?.makeApiCall()
    }

    private fun searchBook() {
        viewModel?.getLiveDataIdObserver()?.observe(this, Observer {
            if (it != null) {
                val book = it.books?.get(0)?.titulo
                tvResultBook.text = book
            } else {
                Toast.makeText(this, "Error in getting book", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel?.makeApiCallId(edtIsbnBook.text.toString())
    }

    private fun initSearch() {
        btnSearchBook.setOnClickListener {
            searchBook()
        }
    }

    private fun showKeyboard() {
        edtIsbnBook.requestFocus()
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_ENTER -> {
                searchBook()
                closeKeyBoard()
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}