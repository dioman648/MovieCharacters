package com.example.moviecharacters.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecharacters.R
import com.example.moviecharacters.model.Result
import kotlinx.android.synthetic.main.item_character.view.*

class CharactersListAdapter : PagingDataAdapter<Result, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
    companion object {

        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
                oldItem == newItem
        }
    }

    inner class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private var onItemClickListener: ((Result) -> Unit)? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = getItem(position)
        holder.itemView.apply {
            item_tv_name.text = character?.name
            Glide.with(this).load(character?.image).into(item_iv_image)

            setOnClickListener {
                onItemClickListener?.let {
                    if (character != null) {
                        it(character)
                    }
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CharactersViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_character,
                parent,
                false
            )
        )
    }
}