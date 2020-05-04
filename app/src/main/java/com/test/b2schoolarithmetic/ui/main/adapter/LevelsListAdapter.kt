package com.test.b2schoolarithmetic.ui.main.adapter


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.b2schoolarithmetic.ui.main.adapter.viewholder.ListItemViewHolder
import com.test.b2schoolarithmetic.ui.main.adapter.viewholder.ListItemViewHolderFactory
import com.test.b2schoolarithmetic.ui.main.vo.ListItem
import org.kodein.di.DKodein
import org.kodein.di.generic.instance

class LevelsListAdapter(
    private val kodein: DKodein,
    private val onItemClick: (Long) -> Unit
) : RecyclerView.Adapter<ListItemViewHolder>() {

    private val items = mutableListOf<ListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return kodein.instance<Int, ListItemViewHolderFactory>(arg = viewType).createViewHolder(parent, onItemClick)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getType
    }

    fun updateData(data: List<ListItem>) {
        items.clear()
        items.addAll(data)

        notifyDataSetChanged()
    }
}