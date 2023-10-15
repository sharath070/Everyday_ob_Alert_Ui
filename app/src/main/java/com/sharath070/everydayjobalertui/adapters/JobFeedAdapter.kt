package com.sharath070.everydayjobalertui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sharath070.everydayjobalertui.R
import com.sharath070.everydayjobalertui.databinding.ItemJobCardBinding
import com.sharath070.everydayjobalertui.model.jobSearchModel.JobFeedItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class JobFeedAdapter : ListAdapter<JobFeedItem, JobFeedAdapter.ViewHolder>(JobFeedDiffUtil()) {

    inner class ViewHolder(val itemBinding: ItemJobCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    class JobFeedDiffUtil: DiffUtil.ItemCallback<JobFeedItem>(){
        override fun areItemsTheSame(oldItem: JobFeedItem, newItem: JobFeedItem): Boolean {
            return oldItem.link === newItem.link
        }

        override fun areContentsTheSame(oldItem: JobFeedItem, newItem: JobFeedItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemJobCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val initialSlug = item.slug
        val replacedSlug = initialSlug.replace("-", " ")
        val slug = replacedSlug.replaceFirstChar { it.uppercase() }

        val title = item.title.rendered
        val relativeDateTime = formatRelativeTime(item.date)

        holder.itemBinding.apply {
            tvSlug.text = slug
            tvTitle.text = title
            tvdate.text = relativeDateTime

            ibBookmark.setOnClickListener {
                item.isSaved = !item.isSaved
                if (item.isSaved){
                    it.setBackgroundResource(R.drawable.bookmark_filled)
                }
                else{
                    it.setBackgroundResource(R.drawable.bookmar_outline)
                }
            }
        }


    }

    private fun formatRelativeTime(dateString: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdf.parse(dateString)
        val currentTime = Date()
        val timeDifference = currentTime.time - date.time

        val seconds = timeDifference / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return when {
            days > 0 -> "$days days ago"
            hours > 0 -> "$hours hours ago"
            minutes > 0 -> "$minutes minutes ago"
            else -> "just now"
        }
    }

}