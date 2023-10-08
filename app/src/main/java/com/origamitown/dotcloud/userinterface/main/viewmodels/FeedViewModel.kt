package com.origamitown.dotcloud.userinterface.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.origamitown.dotcloud.models.post.Post
import com.origamitown.dotcloud.models.user.UserDetails

class FeedViewModel : ViewModel() {

    private val _friendFeedPosts = MutableLiveData<List<Post>>()
    private val friendFeedPosts: LiveData<List<Post>> = _friendFeedPosts

    private val _globalFeedPosts = MutableLiveData<List<Post>>()
    private val globalFeedPosts: LiveData<List<Post>> = _globalFeedPosts

    private val feedPostsState = MutableLiveData(true)
    val feedPosts: LiveData<List<Post>> = feedPostsState.switchMap { state: Boolean -> return@switchMap when (state) {
            true -> friendFeedPosts
            false -> globalFeedPosts
        }
    }

    fun addInitialData() {
        val friendFeedPosts = mutableListOf<Post>(Post.Video("videoUrl", null, 0, null, UserDetails("username", null, 0), 0))
        val globalFeedPosts = mutableListOf<Post>(Post.Ad("@weareserhii", "@weareserhii", null, "Serhii is a good person and a humble creator of this app.", 0))
        _friendFeedPosts.value = friendFeedPosts
        _globalFeedPosts.value = globalFeedPosts
    }

    fun changeFeed(isChecked: Boolean) {
        feedPostsState.value = isChecked
    }
}