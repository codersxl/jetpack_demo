package com.mooc.koltin_demo_test.paging;

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.mooc.koltin_demo_test.R
import java.lang.NullPointerException

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: NetworkStateItemViewHolder
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/19 0019 18:00
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
open class NetworkStateItemViewHolder(
    parent: ViewGroup,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.network_state_item, parent, false)
) {
    private val linearLayout = itemView.findViewById<LinearLayout>(R.id.footer)
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progress_bar)
    private val errorMsg = itemView.findViewById<TextView>(R.id.error_msg)
    private val retry = itemView.findViewById<Button>(R.id.retry_button)
        .also {
            it.setOnClickListener { retryCallback() }
        }

    fun bindTo(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
        retry.isVisible = loadState is Error
        errorMsg.isVisible=  loadState  is NullPointerException
        //linearLayout.isGone=loadState is LoadState.NotLoading

        print("uuu")
        Log.e("000","------$loadState")
    }
}