package com.mercandalli.android.sdk.view_recyclerview_adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * An implementation of an Adapter that already uses a [AdapterDelegatesManager] and calls
 * the corresponding [AdapterDelegatesManager] methods from Adapter's method like [ ][.onCreateViewHolder], [.onBindViewHolder]
 * and [.getItemViewType]. So everything is already setup for you. You just have to add
 * the [AdapterDelegate]s i.e. in the constructor of a subclass that inheritance from this
 * class:
 *
 * ```
 *    class AnimalsAdapter: AbsDelegationAdapter<MyDataSourceType>{
 *        init {
 *            delegatesManager.addDelegate(new FooAdapterDelegate())
 *               .addDelegate(new BarAdapterDelegate())
 *        }
 *    }
 * ```
 *
 * or you can pass a already prepared [AdapterDelegatesManager] via constructor like this:
 *
 * ```
 *    class AnimalsAdapter(
 *       val manager: AdapterDelegatesManager
 *    ): AbsDelegationAdapter<MyDataSourceType>(manager)
`* ```
 *
 * @param <T> The type of the datasource / items
 */
abstract class AbsDelegationAdapter<T> @JvmOverloads constructor(
    protected val delegatesManager: AdapterDelegatesManager<T> = AdapterDelegatesManager()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /** Items / data source of this adapter */
    @JvmField
    var items: T? = null

    /**
     * Adds a list of [AdapterDelegate]s
     *
     * @param delegates Items to add
     */
    constructor(vararg delegates: AdapterDelegate<T>) : this(AdapterDelegatesManager<T>(*delegates))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val items = items ?: throw NullPointerException(
            "Null items. Position: $position. holder: $holder"
        )
        delegatesManager.onBindViewHolder(items, position, holder, null)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val items = items ?: throw NullPointerException(
            "Null items. Position: $position. holder: $holder. payloads.size: ${payloads.size}"
        )
        delegatesManager.onBindViewHolder(items, position, holder, payloads)
    }

    override fun getItemViewType(position: Int): Int {
        val items = items ?: throw NullPointerException("Null items. Position: $position")
        return delegatesManager.getItemViewType(items, position)
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
}
