package com.gen28.fetchrewards.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gen28.fetchrewards.R

/**
 * A fragment representing a list of Rewards.
 */
class RewardFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_reward_list, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.rewards.observe(viewLifecycleOwner, { list ->
            // Set the adapter
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = RewardRecyclerViewAdapter(list)
                }
            }
        })
        viewModel.fetchRewards()
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                RewardFragment()
    }
}