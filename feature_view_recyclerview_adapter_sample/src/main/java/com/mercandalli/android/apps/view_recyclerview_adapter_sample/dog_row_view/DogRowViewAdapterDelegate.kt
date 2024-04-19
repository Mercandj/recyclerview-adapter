package com.mercandalli.android.apps.view_recyclerview_adapter_sample.dog_row_view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mercandalli.android.sdk.view_recyclerview_adapter.AbsListItemAdapterDelegate

class DogRowViewAdapterDelegate :
    AbsListItemAdapterDelegate<Any, Any, DogRowViewAdapterDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: List<Any>, position: Int) = item is DogRowViewModel

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = DogRowView(parent.context)
        view.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: List<Any>) {
        viewHolder.view.setViewModel(item as DogRowViewModel)
    }

    class ViewHolder(val view: DogRowView) : RecyclerView.ViewHolder(view)
}
