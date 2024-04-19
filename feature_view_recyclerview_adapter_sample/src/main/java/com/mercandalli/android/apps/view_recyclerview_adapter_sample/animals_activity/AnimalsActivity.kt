package com.mercandalli.android.apps.view_recyclerview_adapter_sample.animals_activity

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mercandalli.android.apps.view_recyclerview_adapter_sample.R

class AnimalsActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by bind(R.id.animals_activity_recycler_view)
    private val adapter = AnimalsActivityAdapter()
    private val userAction by lazy { createUserAction() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animals_activity)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        userAction.onCreate()
    }

    private fun createScreen() = object : AnimalsActivityContract.Screen {
        override fun setViewModels(viewModels: List<Any>) {
            adapter.setViewModels(viewModels = viewModels)
        }
    }

    private fun createUserAction(): AnimalsActivityContract.UserAction {
        return AnimalsActivityPresenter(
            createScreen()
        )
    }

    companion object {

        private fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
            return lazy(LazyThreadSafetyMode.NONE) { findViewById(res) }
        }
    }
}
