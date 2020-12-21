package com.mooc.koltin_demo_test.adapter;

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mooc.koltin_demo_test.R
import com.mooc.koltin_demo_test.ben.MapData
import com.mooc.koltin_demo_test.databinding.HomeItemFragmentBinding

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: Myadapter
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/17 0017 20:13
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
class Myadapter:RecyclerView.Adapter<Myadapter.MyViewHolder>() {

    //函数式点击事件
   var onclicks:((postion: Int)->Unit?)?=null
   var OnClickListen:OnClickListener?=null

    fun setOnItemClick(OnClickListen:OnClickListener?){
        this.OnClickListen=OnClickListen
    }
    fun setOnItemClick(onclick: (postion: Int) -> Unit){
        this.onclicks=onclick
    }


    val data by lazy {
        mutableListOf<MapData>()
    }

//    companion object{
//        @JvmStatic
//        @BindingAdapter("imasrc")
//        fun setImages(iv:ImageView,imasrc:String?){
//             imasrc?.let {
//                 Glide.with(iv.context).load("http:$imasrc")
//                     .error(R.mipmap.ic_launcher)
//                     .placeholder(R.mipmap.ic_launcher)
//                     .into(iv)
//             }
//        }
//    }

    inner class MyViewHolder(itemView: View, val bind: HomeItemFragmentBinding) : RecyclerView.ViewHolder(
        itemView
    ) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      val bind:HomeItemFragmentBinding= DataBindingUtil.inflate<HomeItemFragmentBinding>(
          LayoutInflater.from(
              parent.context
          ), R.layout.home_item_fragment, parent, false
      );
        return MyViewHolder(bind.root, bind)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val get:MapData= data.get(position)
        Log.e("-----", data.size.toString() + "=============-ffff==========================")
        holder.bind.user=get
        holder.itemView.setOnClickListener {
            onclicks?.invoke(position)

//            OnClickListen?.let {
//                  it.onClick(position)
//            }
        }

    }

    override fun getItemCount(): Int {
        return if (data.size>0) data.size else 0
    }

    fun sumit(it: MutableList<MapData>?) {
                data.clear()
                data.addAll(it!!)
        notifyDataSetChanged()
        Log.e("-----", it?.size.toString() + "==========适配器中=============================")
    }

    /**
     * Interface definition for a callback to be invoked when a view is clicked.
     */
    interface OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        fun onClick(postion: Int)
    }


}
