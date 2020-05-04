package com.test.b2schoolarithmetic.ui.level.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.b2schoolarithmetic.data.remote.dto.AnswerDto

class AnswersAdapter(
    private val itemOnClick: (Long, Boolean) -> Unit
) : RecyclerView.Adapter<AnswerViewHolder>() {

    private val items: MutableList<AnswerDto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder.from(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.bind(items[position], itemOnClick)
    }

    fun updateData(list: List<AnswerDto>) {
        items.clear()
        items.addAll(list)

        notifyDataSetChanged()
    }
}