package com.origamitown.dotcloud.user_interface.main.view_models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.origamitown.dotcloud.models.post.Post
import com.origamitown.dotcloud.models.user.UserDetails
import kotlin.math.log
import kotlin.random.Random

enum class FeedState {
    FRIEND, GLOBAL
}

class FeedViewModel : ViewModel() {
    private val _friendFeedPosts = MutableLiveData<List<Post>>()
    private val friendFeedPosts: LiveData<List<Post>> = _friendFeedPosts

    private val _globalFeedPosts = MutableLiveData<List<Post>>()
    private val globalFeedPosts: LiveData<List<Post>> = _globalFeedPosts

    private val feedPostsState = MutableLiveData<FeedState>(FeedState.FRIEND)
    val feedPosts: LiveData<List<Post>> = feedPostsState.switchMap { state -> return@switchMap when (state) {
            FeedState.FRIEND -> friendFeedPosts
            FeedState.GLOBAL -> globalFeedPosts
        }
    }

    fun changeFeedState() {
        var feedState = feedPostsState.value
        if (feedState == FeedState.FRIEND) {
            feedState = FeedState.GLOBAL
        } else {
            feedState = FeedState.FRIEND
        }
        feedPostsState.value = feedState
    }
}