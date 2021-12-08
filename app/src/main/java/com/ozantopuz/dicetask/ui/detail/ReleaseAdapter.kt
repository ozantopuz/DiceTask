package com.ozantopuz.dicetask.ui.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozantopuz.dicetask.databinding.ItemReleaseBinding
import com.ozantopuz.dicetask.ui.entity.ReleaseViewItem

class ReleaseAdapter(
    private var list: ArrayList<ReleaseViewItem> = arrayListOf()
) : RecyclerView.Adapter<ReleaseAdapter.ReleaseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReleaseViewHolder {
        val itemBinding =
            ItemReleaseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ReleaseViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ReleaseViewHolder, position: Int) {
        val item: ReleaseViewItem = list[position]
        with(holder.binding) {
            textViewTitle.text = item.title
            textViewType.text = item.primaryType
            textViewDate.text = item.firstReleaseDate
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ReleaseViewItem>? = null) {
        if (list != null) this.list = ArrayList(list)
        notifyDataSetChanged()
    }

    class ReleaseViewHolder(val binding: ItemReleaseBinding) :
        RecyclerView.ViewHolder(binding.root)
}