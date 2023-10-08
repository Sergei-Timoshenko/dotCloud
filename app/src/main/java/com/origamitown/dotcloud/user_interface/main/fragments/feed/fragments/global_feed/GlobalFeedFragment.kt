package com.origamitown.dotcloud.user_interface.main.fragments.feed.fragments.global_feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.origamitown.dotcloud.R
import com.origamitown.dotcloud.databinding.FragmentFriendFeedBinding
import com.origamitown.dotcloud.databinding.FragmentGlobalFeedBinding
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.IPostActions
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.PostAdapter
import com.origamitown.dotcloud.user_interface.main.view_models.FeedViewModel

class GlobalFeedFragment(
    private val viewModel: FeedViewModel
) : Fragment() {
    private var _binding: FragmentGlobalFeedBinding? = null
    private val binding get() = _binding!!
    private lateinit var globalFeedAdapter: PostAdapter

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

        setupGlobalRecycler()

        viewModel.feedPosts.observe(viewLifecycleOwner) { posts ->
            globalFeedAdapter.submitList(posts)
        }
    }

    private fun setupGlobalRecycler() {
        val iPostActions = requireParentFragment() as IPostActions
        val globalRecycler = binding.globalFeedRecycler
        globalFeedAdapter = PostAdapter(iPostActions)
        globalRecycler.apply {
            adapter = globalFeedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}