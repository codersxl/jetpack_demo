package com.mooc.koltin_demo_test.paging;

import android.util.Log
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: PostsLoadStateAdapter
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/19 0019 17:59
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
class PostsLoadStateAdapter(
    private val adapter: ArticleAdapter
) : LoadStateAdapter<NetworkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
        Log.e("-----","===================$loadState")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetworkStateItemViewHolder {
        return NetworkStateItemViewHolder(parent) { adapter.retry() }
    }
}