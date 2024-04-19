package com.mercandalli.android.sdk.view_recyclerview_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * A simplified [AdapterDelegate] when the underlying adapter's dataset is a  [ ].
 * This class helps to reduce writing boilerplate code like casting list item and casting
 * ViewHolder.
 *
 * For instance if you have a list of animals (different kind of animals in classes like Cat, Dog
 * etc. assuming all have a common super class Animal) you want to display in your adapter and
 * you want to create a CatAdapterDelegate then this class would look like this:
 *
 * ```
 * class CatRowViewAdapterDelegate :
 *    AbsListItemAdapterDelegate<Any, Any, CatRowViewAdapterDelegate.ViewHolder>() {
 *
 *    override fun isForViewType(item: Any, items: List<Any>, position: Int) = item is CatRowViewModel
 *
 *    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
 *       val view = CatRowView(parent.context)
 *       view.layoutParams = RecyclerView.LayoutParams(
 *           RecyclerView.LayoutParams.MATCH_PARENT,
 *           RecyclerView.LayoutParams.WRAP_CONTENT
 *       )
 *       return ViewHolder(view)
 *    }
 *
 *    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: List<Any>) {
 *       viewHolder.view.setViewModel(item as CatRowViewModel)
 *    }
 *
 *    class ViewHolder(val view: CatRowView) : RecyclerView.ViewHolder(view)
 * }
 * ```
 */
abstract class AbsListItemAdapterDelegate<I : T?, T, VH : RecyclerView.ViewHolder?> :
    AdapterDelegate<List<T>>() {

    override fun isForViewType(items: List<T>, position: Int): Boolean {
        return isForViewType(items[position], items, position)
    }

    override fun onBindViewHolder(
        items: List<T>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        onBindViewHolder(items[position] as I, holder as VH, payloads)
    }

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given item in the
     * list or not
     * element
     *
     * @param item     The item from the list at the given position
     * @param items    The items from adapters dataset
     * @param position The items position in the dataset (list)
     * @return true if this AdapterDelegate is responsible for that, otherwise false
     */
    protected abstract fun isForViewType(item: T, items: List<T>, position: Int): Boolean

    /**
     * Creates the  [RecyclerView.ViewHolder] for the given data source item
     *
     * @param parent The ViewGroup parent of the given datasource
     * @return ViewHolder
     */
    abstract override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    /**
     * Called to bind the [RecyclerView.ViewHolder] to the item of the dataset
     *
     * @param item     The data item
     * @param holder   The ViewHolder
     * @param payloads The payloads
     */
    protected open fun onBindViewHolder(
        item: I,
        viewHolder: VH,
        payloads: List<Any>
    ) {
        // Nothing here for adapter delegate that do not need viewHolder binding
    }
}
