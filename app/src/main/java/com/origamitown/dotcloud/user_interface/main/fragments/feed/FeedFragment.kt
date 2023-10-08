package com.origamitown.dotcloud.user_interface.main.fragments.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.origamitown.dotcloud.databinding.FragmentFeedBinding
import com.origamitown.dotcloud.models.story.Story
import com.origamitown.dotcloud.models.story.StoryPrivacy
import com.origamitown.dotcloud.models.story.UserStoryActivity
import com.origamitown.dotcloud.models.user.UserDetails
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.IPostActions
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.PagerAdapter
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.PostAdapter
import com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters.StoryActivityAdapter
import com.origamitown.dotcloud.user_interface.main.fragments.feed.fragments.friend_feed.FriendFeedFragment
import com.origamitown.dotcloud.user_interface.main.fragments.feed.fragments.global_feed.GlobalFeedFragment
import com.origamitown.dotcloud.user_interface.main.view_models.FeedViewModel

class FeedFragment : Fragment(), IPostActions {
    private val viewModel: FeedViewModel by activityViewModels()
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

        setupPager()
    }

    private fun setupPager() {
        val pagerAdapter = PagerAdapter(this)
        val pager = binding.feedContent.feedPager
        pager.adapter = pagerAdapter
        pagerAdapter.submitFragmentList(
            listOf(
                FriendFeedFragment(viewModel),
                GlobalFeedFragment(viewModel),
            )
        )

        val tabLayout = binding.feedContent.feedTabLayout
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            run {
                val tabTitles = listOf("Friend feed", "Global feed")
                tab.text = tabTitles[position]
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.changeFeedState()
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
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