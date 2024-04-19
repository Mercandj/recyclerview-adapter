package com.mercandalli.android.sdk.view_recyclerview_adapter

/**
 * An adapter implementation designed for items organized in a [List]. This adapter
 * implementation is ready to go. All you have to do is to add [AdapterDelegate]s to the
 * internal [AdapterDelegatesManager] i.e. in the constructor:
 *
 * ```
 * class AnimalsActivityAdapter: ListDelegationAdapter<List<Any>> {
 *
 *    init {
 *       delegatesManager.addDelegate(new FooAdapterDelegate())
 *          .addDelegate(new BarAdapterDelegate());
 *    }
 *
 *     fun setViewModels(viewModels: List<Any>) {
 *         if (items == viewModels) {
 *             return
 *         }
 *         items = viewModels
 *         notifyDataSetChanged()
 *     }
 * }
 * ```
 *
 * @param <T> The type of the items. Must be something that extends from List like List<Foo>
 */
open class ListDelegationAdapter<T : List<*>> : AbsDelegationAdapter<T> {

    constructor() : super()

    constructor(delegatesManager: AdapterDelegatesManager<T>) : super(delegatesManager)

    /**
     * Adds a list of [AdapterDelegate]s
     *
     * @param delegates
     */
    constructor(vararg delegates: AdapterDelegate<T>) : super(*delegates)

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}
