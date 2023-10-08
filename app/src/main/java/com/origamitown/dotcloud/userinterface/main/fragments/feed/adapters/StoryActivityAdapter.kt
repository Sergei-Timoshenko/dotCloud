package com.origamitown.dotcloud.userinterface.main.fragments.feed.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.origamitown.dotcloud.R
import com.origamitown.dotcloud.databinding.ItemUserStoryActivityIconBinding
import com.origamitown.dotcloud.models.story.Story
import com.origamitown.dotcloud.models.story.StoryPrivacy
import com.origamitown.dotcloud.models.story.UserStoryActivity

class StoryActivityAdapter() :
    ListAdapter<UserStoryActivity, StoryActivityAdapter.StoryActivityViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserStoryActivity>() {
            override fun areItemsTheSame(
                oldItem: UserStoryActivity,
                newItem: UserStoryActivity
            ): Boolean {
                return oldItem.userDetails.userId == newItem.userDetails.userId
            }

            override fun areContentsTheSame(
                oldItem: UserStoryActivity,
                newItem: UserStoryActivity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class StoryActivityViewHolder(val binding: ItemUserStoryActivityIconBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("ResourceAsColor")
        fun bind(userStoryActivity: UserStoryActivity) {
            if (userStoryActivity.stories.map { usaStories -> Story(
                storyUrl = usaStories.storyUrl,
                storyPrivacy = usaStories.storyPrivacy,
                storyViewCount = usaStories.storyViewCount,
                userId = usaStories.userId,
                storyId = usaStories.storyId
            )}.map { stories ->  stories.storyPrivacy }.contains(StoryPrivacy.CLOSE_FRIENDS)) {
                binding.root.background.setTint(R.color.story_green)
            } else {
                binding.root.background.setTint(R.color.story_orange)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryActivityViewHolder {
        val binding = ItemUserStoryActivityIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryActivityViewHolder, position: Int) {
        val currentUserStoryActivity = getItem(position)
        holder.bind(currentUserStoryActivity)
    }
}