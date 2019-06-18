package com.example.testhoteles.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<ITEM>(itemView : View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindItem(item : ITEM)
}