package com.tashariko.imageinfo.rv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tashariko.imageinfo.ImageHelper
import com.tashariko.imageinfo.data.ImageInfo
import com.tashariko.imageinfo.R
import kotlinx.coroutines.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ImageInfoAdapter(private val context: Context): ListAdapter<ImageInfo, ImageInfoAdapter.ImageInfoViewHolder>(
    ImageInfoDiffCalback()
){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_img_info,parent,false)
        return ImageInfoViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ImageInfoViewHolder, position: Int) {
        holder.configure(getItem(position),context)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class ImageInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private var nameView:TextView = itemView.findViewById(R.id.nameView)
        private var imageView: ImageView = itemView.findViewById(R.id.imageView)
        private var releaseYearView: TextView = itemView.findViewById(R.id.releaseYearView)
        private var durationView: TextView = itemView.findViewById(R.id.durationView)
        private var createdOnView: TextView = itemView.findViewById(R.id.createdOnView)
        private var shortDescView: TextView = itemView.findViewById(R.id.shortDescView)
        private var descView: TextView = itemView.findViewById(R.id.descView)

        fun configure(
            imgObject: ImageInfo,
            context: Context
        ) {
            nameView.text = imgObject.name
            releaseYearView.text =imgObject.releaseYear.toString()
            durationView.text = imgObject.videoDuration
            shortDescView.text = imgObject.shortDescription
            descView.text = imgObject.description


            GlobalScope.launch {
                val bitmap = async {
                    ImageHelper.decodeSampledBitmapFromResource(context.resources, R.raw.test_image)
                }

                withContext(Dispatchers.Main){
                    imageView.setImageBitmap(bitmap.await())
                }
            }


            val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val output = SimpleDateFormat("dd/MM/yyyy")

            lateinit var d: Date
            try {

                imgObject.updatedOn?.let {
                    d = input.parse(it)
                    val formatted: String = output.format(d)
                    createdOnView.text = formatted
                    createdOnView.visibility = View.VISIBLE
                }

                imgObject.createdOn?.let {
                    d = input.parse(it)
                    val formatted: String = output.format(d)
                    createdOnView.text = formatted
                    createdOnView.visibility = View.VISIBLE
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                createdOnView.visibility = View.GONE
            }

        }

    }
}










