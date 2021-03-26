package com.devcraft.clean_architecture.ui.fragment.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.devcraft.clean_architecture.R
import com.devcraft.clean_architecture.model.Categories
import kotlinx.android.synthetic.main.one_item.view.*

class CategoriesAdapter : BaseQuickAdapter<Categories, BaseViewHolder>(R.layout.one_item) {

    var onItemClickListener: ((position: Int) -> Unit)? = null

    override fun convert(helper: BaseViewHolder, item: Categories?) {
        item?.let { item ->
            helper.itemView.nameData.text = item.title
            helper.itemView.setOnClickListener {
                onItemClickListener?.invoke(helper.adapterPosition)
            }
        }
    }

    fun getCategories(position: Int): Categories? {
        return getItem(position)
    }
}

