package com.origamitown.dotcloud.user_interface.main.fragments.feed.adapters

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Fragment>() {
            override fun areItemsTheSame(oldItem: Fragment, newItem: Fragment): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Fragment, newItem: Fragment): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val differ = AsyncListDiffer<Fragment>(this, DIFF_CALLBACK)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return differ.currentList[position]
    }

    fun submitFragmentList(fragments: List<Fragment>) {
        differ.submitList(fragments)
    }
}