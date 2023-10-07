package com.origamitown.dotcloud.userinterface.main.fragments.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.origamitown.dotcloud.R
import com.origamitown.dotcloud.databinding.FragmentFeedBinding
import com.origamitown.dotcloud.models.comment.Comment
import com.origamitown.dotcloud.models.post.Post
import com.origamitown.dotcloud.models.user.UserDetails
import com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters.pageradapters.FeedPagerAdapter
import com.origamitown.dotcloud.userinterface.main.fragments.feed.pagerfragments.friendfeed.FriendFeedFragment
import com.origamitown.dotcloud.userinterface.main.fragments.feed.pagerfragments.globalfeed.GlobalFeedFragment

const val PAGE_NUM = 2

class FeedFragment : Fragment() {

    private val viewModel by viewModels<FeedViewModel>()
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

        // Setup pager with tab layout
        val feedPagerAdapter = FeedPagerAdapter(this)
        feedPagerAdapter.addFragmentAndList(FriendFeedFragment())
        feedPagerAdapter.addFragmentAndList(GlobalFeedFragment())
        val pager = binding.feedPager
        pager.adapter = feedPagerAdapter
        pager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        feedPagerAdapter.setFriendPostList(viewModel.friendPosts.value ?: listOf())
                        feedPagerAdapter.notifyDataSetChanged()
                    }
                    1 -> {
                        feedPagerAdapter.setGlobalPostList(viewModel.globalPosts.value ?: listOf())
                        feedPagerAdapter.notifyDataSetChanged()
                    }
                }
            }
        })

        val tabLayout = binding.feedTabLayout
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            val categories = listOf("Friends", "Global")
            tab.text = categories[position]
        }.attach()


        // Set initial data
        val friendPosts = listOf<Post>(
            Post.Ad("adUrl", 0),
            Post.Photo("photoUrl", null, 0, null, UserDetails("username", null, 0), 0),
            Post.Video("videoUrl", null, 0, null, UserDetails("username", null, 0), 0)
        )
        viewModel.setFriendPosts(friendPosts)
        val globalPosts = listOf<Post>(
            Post.Video("videoUrl", null, 0, null, UserDetails("username", null, 0), 0),
            Post.Photo("photoUrl", null, 0, null, UserDetails("username", null, 0), 0),
            Post.Ad("adUrl", 0),
        )
        viewModel.setFriendPosts(globalPosts)

        viewModel.friendPosts.observe(viewLifecycleOwner) { postList ->
            run {
                feedPagerAdapter.setFriendPostList(postList)
                feedPagerAdapter.notifyDataSetChanged()
            }
        }
        viewModel.globalPosts.observe(viewLifecycleOwner) { postList ->
            run {
                feedPagerAdapter.setGlobalPostList(postList)
                feedPagerAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}