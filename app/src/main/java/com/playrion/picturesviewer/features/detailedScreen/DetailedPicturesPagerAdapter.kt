package com.playrion.picturesviewer.features.detailedScreen

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.playrion.picturesviewer.databinding.ItemDetailedBinding
import com.playrion.picturesviewer.domain.models.FlickrPicture
import com.playrion.picturesviewer.extensions.asDataTime
import com.playrion.picturesviewer.extensions.loadImageWithPlaceHolder

class DetailedPicturesPagerAdapter() : ListAdapter<FlickrPicture,
        DetailedPicturesPagerAdapter.ItemViewHolder>(ItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ItemViewHolder(private val binding: ItemDetailedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @Suppress("DEPRECATION")
        fun bind(data: FlickrPicture) {
            with(binding) {
                title.text = data.title
                description.text = Html.fromHtml(data.description)
                description.movementMethod = LinkMovementMethod.getInstance()
                uploadDate.text = data.uploadDate.asDataTime()
                image.loadImageWithPlaceHolder(data.imageUrl)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDetailedBinding
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