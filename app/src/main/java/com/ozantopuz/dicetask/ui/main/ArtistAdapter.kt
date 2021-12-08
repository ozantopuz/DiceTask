package com.ozantopuz.dicetask.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozantopuz.dicetask.databinding.ItemArtistBinding
import com.ozantopuz.dicetask.ui.entity.ArtistViewItem

class ArtistAdapter(
    private var list: ArrayList<ArtistViewItem> = arrayListOf(),
    private var block: (ArtistViewItem) -> Unit
) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistViewHolder {
        val itemBinding =
            ItemArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ArtistViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val item: ArtistViewItem = list[position]
        with(holder.binding) {
            textViewName.text = item.name
            textViewScore.text = item.score
        }

        holder.itemView.setOnClickListener { block.invoke(item) }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<ArtistViewItem>? = null) {
        if (list != null) this.list.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ArtistViewItem>? = null) {
        if (list != null) this.list = ArrayList(list)
        notifyDataSetChanged()
    }

    class ArtistViewHolder(val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root)
}