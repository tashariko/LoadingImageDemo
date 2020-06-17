package com.tashariko.imageinfo.rv

import androidx.recyclerview.widget.DiffUtil
import com.tashariko.imageinfo.data.ImageInfo

class ImageInfoDiffCalback : DiffUtil.ItemCallback<ImageInfo>() {
    override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean = oldItem.id == newItem.id

}