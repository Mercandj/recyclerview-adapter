package com.mercandalli.android.apps.view_recyclerview_adapter_sample.cat_row_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import com.mercandalli.android.apps.view_recyclerview_adapter_sample.R

class CatRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val view = View.inflate(context, R.layout.cat_row_view, this)
    private val name: TextView = bind(R.id.cat_row_view_name)
    private val userAction = createUserAction()

    fun setViewModel(viewModel: CatRowViewModel?) {
        userAction.onViewModelSet(viewModel = viewModel)
    }

    private fun <T : View> bind(@Suppress("SameParameterValue") @IdRes id: Int): T {
        return view.findViewById(id)
    }

    private fun createScreen() = object : CatRowViewContract.Screen {
        override fun setName(text: String) {
            name.text = text
        }
    }

    private fun createUserAction(): CatRowViewContract.UserAction {
        if (isInEditMode) {
            return object : CatRowViewContract.UserAction {
                override fun onViewModelSet(viewModel: CatRowViewModel?) {}
            }
        }
        return CatRowViewPresenter(
            createScreen()
        )
    }
}
