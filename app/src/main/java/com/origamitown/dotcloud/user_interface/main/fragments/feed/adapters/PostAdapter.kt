package com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.origamitown.dotcloud.databinding.ItemAdPostBinding
import com.origamitown.dotcloud.databinding.ItemFeedBinding
import com.origamitown.dotcloud.models.post.Post
import java.lang.Error

enum class ViewType {
    PHOTO, VIDEO, AD
}

class PostAdapter(
    val iPostActions: IPostActions
) :
    androidx.recyclerview.widget.ListAdapter<Post, PostAdapter.FeedViewHolder>(
        DIFF_CALLBACK
    ) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                if (oldItem::class != newItem::class) {
                    return false
                }
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                if (oldItem::class != newItem::class) {
                    return false
                }
                if (oldItem::class == Post.Photo::class) {
                    return (oldItem as Post.Photo).photoId == (newItem as Post.Photo).photoId
                }
                if (oldItem::class == Post.Video::class) {
                    return (oldItem as Post.Video).videoId == (newItem as Post.Video).videoId
                }
                if (oldItem::class == Post.Ad::class) {
                    return (oldItem as Post.Ad).adId == (newItem as Post.Ad).adId
                }
                return true
            }
        }
    }

    sealed class FeedViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        class PhotoViewHolder(private val binding: ItemFeedBinding) : FeedViewHolder(binding.root) {
            fun bind(photo: Post.Photo) {
                binding.itemFeedDetails.text = photo.toString()
            }
        }
        class VideoViewHolder(private val binding: ItemFeedBinding) : FeedViewHolder(binding.root) {
            fun bind(video: Post.Video) {
                binding.itemFeedDetails.text = video.toString()
            }
        }
        class AdViewHolder(val binding: ItemAdPostBinding) : FeedViewHolder(binding.root) {
            fun bind(ad: Post.Ad) {
                binding.adName.text = ad.adName
                binding.adDescription.text = ad.adDescription ?: ""
                ad.adPhotoUrl?.let { url ->

                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Post.Photo -> ViewType.PHOTO.ordinal
            is Post.Video -> ViewType.VIDEO.ordinal
            is Post.Ad -> ViewType.AD.ordinal
            else -> throw Error()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return when (viewType) {
            ViewType.PHOTO.ordinal -> {
                val binding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FeedViewHolder.PhotoViewHolder(binding)
            }
            ViewType.VIDEO.ordinal -> {
                val binding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FeedViewHolder.VideoViewHolder(binding)
            }
            ViewType.AD.ordinal -> {
                val binding = ItemAdPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FeedViewHolder.AdViewHolder(binding)
            }
            else -> {
                throw Error()
            }
        }
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val currentPost = getItem(position)
        when (currentPost) {
            is Post.Photo -> (holder as FeedViewHolder.PhotoViewHolder).bind(currentPost)
            is Post.Video -> (holder as FeedViewHolder.VideoViewHolder).bind(currentPost)
            is Post.Ad -> {
                val holder = holder as FeedViewHolder.AdViewHolder
                holder.binding.root.setOnClickListener {
                    if (position != RecyclerView.NO_POSITION) {
                        iPostActions.navigateToAdUrl(currentPost.adUrl)
                    }
                }
                holder.bind(currentPost)
            }
            else -> throw Error()
        }
    }
}

interface IPostActions {
    fun openPhotoPreview()
    fun openVideoPreview()
    fun navigateToAdUrl(adUrl: String)
}