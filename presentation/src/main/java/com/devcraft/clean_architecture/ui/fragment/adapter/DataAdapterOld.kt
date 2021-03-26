package com.devcraft.clean_architecture.ui.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.model.AllData
import kotlinx.android.synthetic.main.one_item.view.*

class DataAdapterOld : RecyclerView.Adapter<DataAdapterOld.ViewHolder>() {

    var onItemClickListener: ((position: Int) -> Unit)? = null


    var items = mutableListOf<AllData>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.one_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: AllData) = itemView.run {
            nameData.text = item.title
            itemView.setOnClickListener {
                onItemClickListener?.invoke(adapterPosition)
            }
        }
    }

   /* fun getBankCard(position: Int): BankCard? {
        return getItem(position)
    }*/
}