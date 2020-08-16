package com.felix.lib_arch.adp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/14
 * @Des: BaseAdp
 */
abstract class BaseAdp<T> : RecyclerView.Adapter<BaseAdp.CommonVH>() {

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

    abstract val onDataChangeListenner: (View, T, Int, Int) -> Unit
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonVH =
        parent.context.let {
            LayoutInflater.from(it)
        }.let {
            it.inflate(layoutId, parent, false)
        }.let { view ->
            CommonVH(view)
//        javaClass.genericSuperclass.takeIf { it is ParameterizedType }?.let {
//            it as ParameterizedType
//        }?.let {
//            it.actualTypeArguments
//        }?.let {
//            it.getOrNull(1)
//        }?.let {
//            it as Class<VH>
//        }?.let {
//            it.getDeclaredConstructor(View::class.java).let {
//                it.runCatching {
//                    newInstance(this, view)
//                }.getOrDefault(it.newInstance(view))
//            }
//        }!!
        }

    var onItemClickListenner: ((view: View, data: T, position: Int, size: Int) -> Unit)? = null
        get


    override fun getItemCount() = datas.size

    override fun onBindViewHolder(holder: CommonVH, position: Int) {
        onDataChangeListenner.invoke(holder.itemView, datas[position], position, datas.size)
//        holder.onDataChange(datas[position], position, datas.size)
        holder.itemView.setOnClickListener {
            onItemClickListenner?.invoke(it, datas[position], position, datas.size)
        }
    }

    class CommonVH(view: View) : RecyclerView.ViewHolder(view) {
    }
}