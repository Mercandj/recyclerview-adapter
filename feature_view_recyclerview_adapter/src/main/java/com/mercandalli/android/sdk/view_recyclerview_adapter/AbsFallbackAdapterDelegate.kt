package com.mercandalli.android.sdk.view_recyclerview_adapter

/**
 * This class can be used as base class for a fallback delegate [ ][AdapterDelegatesManager.setFallbackDelegate].
 */
abstract class AbsFallbackAdapterDelegate<T> : AdapterDelegate<T>() {

    /**
     * Not needed, because never called for fallback adapter delegates.
     *
     * @param items The data source of the Adapter
     * @param position The position in the datasource
     * @return true
     */
    override fun isForViewType(items: T, position: Int): Boolean {
        return true
    }
}
