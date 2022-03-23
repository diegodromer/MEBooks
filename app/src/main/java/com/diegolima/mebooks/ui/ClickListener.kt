package com.diegolima.mebooks.ui

import android.view.View
import com.diegolima.mebooks.domain.models.Book

interface ClickListener {
    fun onItemClick(view: View, book: Book?)
}