package com.talib.youtubeclone.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.talib.youtubeclone.R
import com.talib.youtubeclone.ui.home.YoutubeAdapter.ItemViewType.ITEM_POST_TYPE
import com.talib.youtubeclone.ui.home.YoutubeAdapter.ItemViewType.ITEM_VIDEO_TYPE

class YoutubeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    object ItemViewType{
        const val ITEM_VIDEO_TYPE = 1
        const val ITEM_POST_TYPE = 0
    }

    private val differCallback = object : DiffUtil.ItemCallback<YoutubeModel>() {
        override fun areItemsTheSame(oldItem: YoutubeModel, newItem: YoutubeModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: YoutubeModel, newItem: YoutubeModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun getItemViewType(position: Int): Int {
        return if (differ.currentList[position].isPost == true){
            ITEM_POST_TYPE
        }else{
            ITEM_VIDEO_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ITEM_VIDEO_TYPE){
            return YoutubeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.youtube_custom_layout,parent,false))
        }else{
            return YoutubePostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.youtube_custom_layout2,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is YoutubeViewHolder){
            holder.thumbnailImage.load(differ.currentList.getOrNull(position)?.thumbImg){
                crossfade(true)
            }
            holder.title.text = differ.currentList.getOrNull(position)?.title
            holder.desc.text = differ.currentList.getOrNull(position)?.desc
            holder.authorImage.load(differ.currentList.getOrNull(position)?.authImg){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }else if (holder is YoutubePostViewHolder){
            holder.postImage.load(differ.currentList.getOrNull(position)?.thumbImg){
                crossfade(true)
            }
            holder.postTitle.text = differ.currentList.getOrNull(position)?.title
            holder.postDesc.text = differ.currentList.getOrNull(position)?.title
        }

        holder.itemView.setOnClickListener {
            differ.currentList.getOrNull(position)?.let {
                onItemClickListener?.invoke(it)
            }
        }

    }

    private var onItemClickListener: ((YoutubeModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (YoutubeModel) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int = differ.currentList.size



    inner class YoutubeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnailImage: ImageView = itemView.findViewById(R.id.thumbnailImage)
        val authorImage: ImageView = itemView.findViewById(R.id.authorImage)
        val title: TextView = itemView.findViewById(R.id.videoTitle)
        val desc: TextView = itemView.findViewById(R.id.videoDesc)
    }

    inner class YoutubePostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postImage: ImageView = itemView.findViewById(R.id.postImage)
        val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        val postDesc: TextView = itemView.findViewById(R.id.postDesc)
    }
}