package com.mercandalli.android.apps.view_recyclerview_adapter_sample.dog_row_view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.IdRes
import com.mercandalli.android.apps.view_recyclerview_adapter_sample.R

class DogRowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val view = View.inflate(context, R.layout.dog_row_view, this)
    private val name: TextView = bind(R.id.dog_row_view_name)
    private val userAction = createUserAction()

    fun setViewModel(viewModel: DogRowViewModel?) {
        userAction.onViewModelSet(viewModel = viewModel)
    }

    private fun <T : View> bind(@Suppress("SameParameterValue") @IdRes id: Int): T {
        return view.findViewById(id)
    }

    private fun createScreen() = object : DogRowViewContract.Screen {
        override fun setName(text: String) {
            name.text = text
        }
    }

    private fun createUserAction(): DogRowViewContract.UserAction {
        if (isInEditMode) {
            return object : DogRowViewContract.UserAction {
                override fun onViewModelSet(viewModel: DogRowViewModel?) {}
            }
        }
        return DogRowViewPresenter(
            createScreen()
        )
    }
}
