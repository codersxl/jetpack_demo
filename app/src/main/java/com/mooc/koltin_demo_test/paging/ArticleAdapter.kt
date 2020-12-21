package com.mooc.koltin_demo_test.paging;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mooc.koltin_demo_test.R
import com.mooc.koltin_demo_test.ben.MapData

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: ArticleAdapter
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/19 0019 17:29
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
class ArticleAdapter : PagingDataAdapter<MapData, ArticleAdapter.ArticleViewHolder>(POST_COMPARATOR){

    companion object{
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<MapData>() {
            override fun areContentsTheSame(oldItem: MapData, newItem: MapData): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: MapData, newItem: MapData): Boolean =
                oldItem.category_id == newItem.category_id
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.tvName.text = getItem(position)?.title
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false))
    }
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tvname)
    }

}


