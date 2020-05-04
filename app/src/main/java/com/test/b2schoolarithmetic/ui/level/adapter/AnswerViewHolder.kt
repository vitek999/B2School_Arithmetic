package com.test.b2schoolarithmetic.ui.level.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.test.b2schoolarithmetic.R
import com.test.b2schoolarithmetic.data.remote.dto.AnswerDto

class AnswerViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val btnAnswer: Button = itemView.findViewById(R.id.answer_button)

    fun bind(item: AnswerDto) {
        btnAnswer.text = item.text
    }

    companion object {
        fun from(parent: ViewGroup): AnswerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return AnswerViewHolder(layoutInflater.inflate(R.layout.answer_button_list_item, parent, false))
        }
    }
}