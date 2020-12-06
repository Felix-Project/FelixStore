package com.felix.arch.adp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felix.arch.mvvm.ITAG

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: BaseAdp
 */
abstract class BaseAdp<T> : RecyclerView.Adapter<BaseAdp.CommonVH>(), ITAG {

    var datas = mutableListOf<T>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    fun addData(vararg data: T) {
        datas.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: List<T>) {
        datas.addAll(data)
        notifyDataSetChanged()
    }


    abstract val layoutId: Int

    abstract fun onDataChange(view: View, data: T, pos: Int, size: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH =
        parent.context.let {
            LayoutInflater.from(it)
        }.inflate(layoutId, parent, false).let { view ->
            CommonVH(view)
        }

    var onItemClickListener: ((view: View, data: T, position: Int, size: Int) -> Unit)? = null


    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        onDataChange(holder.itemView, datas[position], position, datas.size)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(it, datas[position], position, datas.size)
        }
    }

    class CommonVH(view: View) : RecyclerView.ViewHolder(view)
}