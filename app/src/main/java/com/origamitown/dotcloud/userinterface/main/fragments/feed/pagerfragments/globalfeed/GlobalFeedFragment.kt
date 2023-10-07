package com.origamitown.dotcloud.userinterface.main.fragments.feed.pagerfragments.globalfeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.origamitown.dotcloud.databinding.FragmentGlobalFeedBinding
import com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters.recycleradapters.FeedRecyclerAdapter

class GlobalFeedFragment : Fragment() {

    private var _binding: FragmentGlobalFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGlobalFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val recyclerAdapter = FeedRecyclerAdapter()
        val recycler = binding.globalFeedRecycler
        recycler.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        recyclerAdapter.submitList(args?.getParcelableArrayList("post_list"))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}