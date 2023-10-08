package com.origamitown.dotcloud.user_interface.main.fragments.feed.fragments.friend_feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.origamitown.dotcloud.R
import com.origamitown.dotcloud.databinding.FragmentFriendFeedBinding
import com.origamitown.dotcloud.databinding.FragmentProfileBinding
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.IPostActions
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.PostAdapter
import com.origamitown.dotcloud.user_interface.main.view_models.FeedViewModel

class FriendFeedFragment(
    private val viewModel: FeedViewModel
) : Fragment() {
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

        val iPostActions = requireParentFragment() as IPostActions
        val friendRecycler = binding.friendFeedRecycler
        val friendFeedAdapter = PostAdapter(iPostActions)
        friendRecycler.apply {
            adapter = friendFeedAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.feedPosts.observe(viewLifecycleOwner) { posts ->
            friendFeedAdapter.submitList(posts)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}