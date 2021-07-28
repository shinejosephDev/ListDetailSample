package com.ccd.listdetailsample.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ccd.listdetailsample.business.model.Article
import com.ccd.listdetailsample.databinding.LayoutListItemBinding

class ListAdapter(
    private val context: Context,
    private val interaction: Interaction,
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val binding =
            LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList.get(position))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Article>) {
        differ.submitList(list)
    }

    class ViewHolder(
        private val binding: LayoutListItemBinding,
        private val interaction: Interaction?,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) = with(itemView) {
            setOnClickListener {
                interaction?.onItemSelected(adapterPosition, article)
            }

            binding.title.text = article.title
            binding.byline.text = article.byline
            binding.date.text = article.published_date

            binding.image.load(article.media.get(0).metadata.get(0).url){
                transformations(CircleCropTransformation())
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Article)
    }

}