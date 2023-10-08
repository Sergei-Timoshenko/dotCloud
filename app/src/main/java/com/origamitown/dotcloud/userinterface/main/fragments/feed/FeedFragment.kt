package com.origamitown.dotcloud.userinterface.main.fragments.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.origamitown.dotcloud.R
import com.origamitown.dotcloud.databinding.FragmentFeedBinding
import com.origamitown.dotcloud.models.story.Story
import com.origamitown.dotcloud.models.story.StoryPrivacy
import com.origamitown.dotcloud.models.story.UserStoryActivity
import com.origamitown.dotcloud.models.user.UserDetails
import com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters.IPostActions
import com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters.PostAdapter
import com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters.StoryActivityAdapter
import com.origamitown.dotcloud.userinterface.main.viewmodels.FeedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedFragment : Fragment(), IPostActions {

    private val viewModel by activityViewModels<FeedViewModel>()
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup post recycler
        val recycler = binding.feedRecycler
        val postAdapter = PostAdapter(this)
        recycler.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Setup user story activity adapter
        val storyRecycler = binding.feedStoryRecycler
        val storyActivityAdapter = StoryActivityAdapter()
        recycler.apply {
            adapter = storyActivityAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        storyActivityAdapter.submitList(
            listOf(
                UserStoryActivity(
                    listOf(Story("", StoryPrivacy.FRIENDS, 0, 0, 0)),
                    UserDetails("", null, 0)
                ),
                UserStoryActivity(
                    listOf(Story("", StoryPrivacy.CLOSE_FRIENDS, 0, 0, 0)),
                    UserDetails("", null, 0)
                )
            )
        )


        binding.feedSwitcher.setOnCheckedChangeListener { _, isChecked ->
            val feedRecyclerParams = binding.feedRecycler.layoutParams as ConstraintLayout.LayoutParams
            viewModel.changeFeed(isChecked)
            if (isChecked) {
                binding.feedStoriesHolder.visibility = View.VISIBLE
                feedRecyclerParams.topToBottom = binding.feedStoriesHolder.id
                binding.feedRecycler.requestLayout()
            } else {
                binding.feedStoriesHolder.visibility = View.GONE
                feedRecyclerParams.topToBottom = binding.feedSwitcher.id
                binding.feedRecycler.requestLayout()
            }
        }

        viewModel.feedPosts.observe(viewLifecycleOwner) { posts ->
            postAdapter.submitList(posts)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun openPhotoPreview() {
        TODO("Not yet implemented")
    }

    override fun openVideoPreview() {
        TODO("Not yet implemented")
    }

    override fun navigateToAdUrl(adUrl: String) {
        if (adUrl.startsWith('@')) {
            Toast.makeText(requireContext(), adUrl, Toast.LENGTH_SHORT).show()
        } else {
            TODO("Open web browser")
        }
    }
}