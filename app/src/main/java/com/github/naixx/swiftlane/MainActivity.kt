package com.github.naixx.swiftlane

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.naixx.swiftlane.app.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModeFactory: ViewModelFactory<SwiftlaneViewModel>

    private val adapter by lazy { HitsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBar(toolbar)

        recyclerView.adapter = adapter

        val viewModel = ViewModelProviders.of(this, viewModeFactory).get(SwiftlaneViewModel::class.java)

        viewModel.list.observe(this, Observer {
            adapter.submitList(it)
        })
        viewModel.loading.observe(this, Observer {
            progressBar.isVisible = it
        })
        val position = savedInstanceState?.getInt(SELECTION) ?: 0
        recyclerView.scrollToPosition(position)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTION, (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
    }

    companion object {
        const val SELECTION = "selection"
    }
}

