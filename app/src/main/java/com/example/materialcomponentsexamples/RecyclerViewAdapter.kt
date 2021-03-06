package com.example.materialcomponentsexamples

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.materialcomponentsexamples.databinding.RecyclerItemBinding

/**
 *
 *
 * @author wangzhichao
 * @date 8/4/20
 */
class RecyclerViewAdapter(private val list: List<Data>, private val itemCallback: (Data) -> Unit) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, itemCallback)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(
        private val binding: RecyclerItemBinding,
        private val itemCallback: (Data) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Data) {
            binding.tvTitle.text = item.title
            if (!TextUtils.isEmpty(item.subtitle)) {
                binding.tvSubtitle.visibility = View.VISIBLE
                binding.tvSubtitle.text = item.subtitle
            } else {
                binding.tvSubtitle.visibility = View.GONE
            }
            itemView.setOnClickListener {
                itemCallback.invoke(item)
            }
        }

        companion object {
            fun from(parent: ViewGroup, itemCallback: (Data) -> Unit): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RecyclerItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding, itemCallback)
            }
        }
    }
}