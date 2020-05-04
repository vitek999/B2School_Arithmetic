package com.test.b2schoolarithmetic.ui.account.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.b2schoolarithmetic.data.remote.dto.ClassGroupsDto

class GroupAdapter : RecyclerView.Adapter<GroupViewHolder>() {

    private val items: MutableList<ClassGroupsDto> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        return GroupViewHolder.from(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateData(data: List<ClassGroupsDto>) {
        items.clear()
        items.addAll(data)

        notifyDataSetChanged()
    }
}