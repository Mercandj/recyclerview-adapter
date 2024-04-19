package com.mercandalli.android.sdk.view_recyclerview_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * An implementation of an Adapter that already uses a [AdapterDelegatesManager] pretty same as
 * [AbsDelegationAdapter] but also uses [AsyncListDiffer] from support library 27.0.1 for
 * calculating diffs between old and new collections of items and does this on background thread.
 * That means that now you should not carry about [RecyclerView.Adapter.notifyItemChanged]
 * and other methods of adapter, all you need to do is to submit a new list into adapter and all diffs will be
 * calculated for you.
 * You just have to add the [AdapterDelegate]s i.e. in the constructor of a subclass that inheritance from this
 * class:
 *
 * ```
 * class AnimalsAdapter: AsyncListDifferDelegationAdapter<MyDataSourceType> {
 *
 *    init {
 *       delegatesManager.addDelegate(FooAdapterDelegate())
 *          .addDelegate(new BarAdapterDelegate())
 *    }
 * }
 * ```
 *
 * @param <T> The type of the datasource / items. Internally we will use List&lt;T&gt; but you only have
 * to provide T (and not List&lt;T&gt;). Its safe to use this with [AbsListItemAdapterDelegate].
 */
class AsyncListDifferDelegationAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private val delegatesManager: AdapterDelegatesManager<List<T>>
    private val differ: AsyncListDiffer<T>

    @JvmOverloads
    constructor(
        diffCallback: DiffUtil.ItemCallback<T>,
        delegatesManager: AdapterDelegatesManager<List<T>> = AdapterDelegatesManager()
    ) {
        this.differ = AsyncListDiffer(this, diffCallback)
        this.delegatesManager = delegatesManager
    }

    constructor(
        differConfig: AsyncDifferConfig<T>,
        delegatesManager: AdapterDelegatesManager<List<T>>
    ) {
        this.differ = AsyncListDiffer<T>(AdapterListUpdateCallback(this), differConfig)
        this.delegatesManager = delegatesManager
    }

    /**
     * Adds a list of [AdapterDelegate]s
     *
     * @param delegates
     */
    constructor(
        diffCallback: DiffUtil.ItemCallback<T>,
        vararg delegates: AdapterDelegate<List<T>>
    ) {
        differ = AsyncListDiffer(this, diffCallback)
        delegatesManager = AdapterDelegatesManager(*delegates)
    }

    /**
     * Adds a list of [AdapterDelegate]s
     *
     * @param delegates
     */
    constructor(
        differConfig: AsyncDifferConfig<T>,
        vararg delegates: AdapterDelegate<List<T>>
    ) {
        differ = AsyncListDiffer<T>(AdapterListUpdateCallback(this), differConfig)
        delegatesManager = AdapterDelegatesManager(*delegates)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(differ.currentList, position, holder, null)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: List<Any>
    ) {
        delegatesManager.onBindViewHolder(differ.currentList, position, holder, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(differ.currentList, position)
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewRecycled(holder)
    }

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean {
        return delegatesManager.onFailedToRecycleView(holder)
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        delegatesManager.onViewDetachedFromWindow(holder)
    }

    var items: List<T>?
        /**
         * Get the items / data source of this adapter
         *
         * @return The items / data source
         */
        get() = differ.currentList
        /**
         * Set the items / data source of this adapter
         *
         * @param items The items / data source
         */
        set(items) {
            differ.submitList(items)
        }

    /**
     * Set the items / data source of this adapter
     *
     * @param items The items / data source
     * @param commitCallback Runnable that is executed when the List is committed, if it is committed
     */
    fun setItems(items: List<T>?, commitCallback: Runnable?) {
        differ.submitList(items, commitCallback)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
