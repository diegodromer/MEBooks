package com.diegolima.mebooks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.mebooks.R
import com.diegolima.mebooks.domain.models.EditorialBooks
import com.diegolima.mebooks.ui.ClickListener
import com.google.android.material.card.MaterialCardView

class EditorialBookViewAdapter (private val listener: ClickListener) :
    RecyclerView.Adapter<EditorialBookViewAdapter.BookViewHolder?>() {
    private var booksList: EditorialBooks? = null

    fun setBooksList(booksList: EditorialBooks?) {
        this.booksList = booksList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        //holder.bind(booksList?.books?.get(position)!!)
        val book = booksList?.books?.get(position)
        holder.isbn.text = book?.isbn
        holder.title.text = book?.titulo
        holder.subtitle.text = book?.subtitulo

        holder.cardView.setOnClickListener {
            listener.onItemClick(holder.cardView, book)
        }
    }

    override fun getItemCount(): Int {
        return if (booksList == null){
            0
        }else{
            booksList?.books!!.size
        }
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val isbn: TextView = itemView.findViewById(R.id.tvIsbn)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val subtitle: TextView = itemView.findViewById(R.id.tvSubTitle)
        val cardView: MaterialCardView = itemView.findViewById(R.id.cardView)
    }
}