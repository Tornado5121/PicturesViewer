package com.playrion.picturesviewer.features.listScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playrion.picturesviewer.databinding.ItemFlickrPictureListBinding
import com.playrion.picturesviewer.domain.models.FlickrPicture
import com.playrion.picturesviewer.extensions.loadImageWithPlaceHolder

const val ITEMS_BEFORE = 6

class PicturesListAdapter(
    private val onClick: (FlickrPicture) -> Unit,
    private val itemReached: () -> Unit,
) : ListAdapter<FlickrPicture,
        PicturesListAdapter.ItemViewHolder>(ItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
        if (position == currentList.size - ITEMS_BEFORE) {
            itemReached()
        }
    }

    class ItemViewHolder(private val binding: ItemFlickrPictureListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FlickrPicture, click: (FlickrPicture) -> Unit) {
            with(binding) {
                title.text = data.title
                image.loadImageWithPlaceHolder(data.thumbnailUrl)
                root.setOnClickListener {
                    click(data)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFlickrPictureListBinding
                    .inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }

    class ItemDiffUtilCallback : DiffUtil.ItemCallback<FlickrPicture>() {
        override fun areItemsTheSame(oldItem: FlickrPicture, newItem: FlickrPicture): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FlickrPicture, newItem: FlickrPicture): Boolean {
            return oldItem == newItem
        }
    }

}