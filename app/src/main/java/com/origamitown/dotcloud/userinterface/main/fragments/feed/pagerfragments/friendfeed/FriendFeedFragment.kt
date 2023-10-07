package com.origamitown.dotcloud.userinterface.main.fragments.feed.pagerfragments.friendfeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.origamitown.dotcloud.R
import com.origamitown.dotcloud.databinding.FragmentFriendFeedBinding
import com.origamitown.dotcloud.models.post.Post
import com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters.recycleradapters.FeedRecyclerAdapter

class FriendFeedFragment : Fragment() {

    private var _binding: FragmentFriendFeedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFriendFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val recyclerAdapter = FeedRecyclerAdapter()
        val recycler = binding.friendFeedRecycler
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