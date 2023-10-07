package com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters.pageradapters

import android.graphics.pdf.PdfDocument.Page
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.origamitown.dotcloud.models.post.Post
import com.origamitown.dotcloud.userinterface.main.fragments.feed.PAGE_NUM
import com.origamitown.dotcloud.userinterface.main.fragments.feed.pagerfragments.friendfeed.FriendFeedFragment
import com.origamitown.dotcloud.userinterface.main.fragments.feed.pagerfragments.globalfeed.GlobalFeedFragment

class FeedPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragmentList = mutableListOf<Fragment>()
    private val friendPostList = mutableListOf<Post>()
    private val globalPostList = mutableListOf<Post>()

    override fun getItemCount(): Int {
        return PAGE_NUM
    }

    fun addFragmentAndList(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    fun setFriendPostList(list: List<Post>) {
        friendPostList.clear()
        friendPostList.addAll(list)
    }

    fun setGlobalPostList(list: List<Post>) {
        globalPostList.clear()
        globalPostList.addAll(list)
    }

    override fun createFragment(position: Int): Fragment {
        val currentFragment = fragmentList[position]
        val args = Bundle();
        when (currentFragment) {
            is FriendFeedFragment -> args.putParcelableArrayList("post_list",  ArrayList<Parcelable>(friendPostList))
            is GlobalFeedFragment -> args.putParcelableArrayList("post_list",  ArrayList<Parcelable>(globalPostList))
        }
        currentFragment.arguments = args
        return currentFragment
    }
}