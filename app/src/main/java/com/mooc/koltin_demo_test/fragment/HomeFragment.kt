package com.mooc.koltin_demo_test.fragment;


import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView
import com.mooc.koltin_demo_test.R
import com.mooc.koltin_demo_test.adapter.Myadapter
import com.mooc.koltin_demo_test.comm.BaseVmFragment
import com.mooc.koltin_demo_test.databinding.HomeFragmentBinding
import com.mooc.koltin_demo_test.paging.ArticleAdapter
import com.mooc.koltin_demo_test.paging.PostsLoadStateAdapter
import com.mooc.koltin_demo_test.utils.State
import com.mooc.koltin_demo_test.viewmodel.HomeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest


/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: HomeFragment
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/17 0017 15:52
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
class HomeFragment : BaseVmFragment<HomeFragmentBinding, HomeViewModel>() {
    override fun getClasss(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }


    val myadapter: Myadapter by lazy {
        Myadapter()
    }

    val articleAdapter: ArticleAdapter by lazy {
        ArticleAdapter()
    }
    override fun getLayout() = R.layout.home_fragment

    override fun initView() {

        val linearLayoutManager = LinearLayoutManager(context);
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rootView.rcy.run {
            layoutManager = linearLayoutManager
            adapter =  articleAdapter.withLoadStateFooter(PostsLoadStateAdapter(articleAdapter))
         //adapter=myadapter
        }
    }

    override fun intEvent() {

        val headerView = SinaRefreshView(context)
        rootView.refreshLayout.run {
            setHeaderView(headerView)
            startRefresh()
            setEnableLoadmore(false)
            setEnableRefresh(true)
            setEnableOverScroll(true)
            setOnRefreshListener(object : RefreshListenerAdapter() {
                override fun onRefresh(refreshLayout: TwinklingRefreshLayout?) {
                    super.onRefresh(refreshLayout)
                   // vModel.loadata()
                    articleAdapter.refresh()
                }

//                override fun onLoadMore(refreshLayout: TwinklingRefreshLayout?) {
//                    super.onLoadMore(refreshLayout)
//                   // vModel.loadMore()
//                    articleAdapter.retry()
//                }
            })
        }


        myadapter.setOnItemClick(object :Myadapter.OnClickListener{
            override fun onClick(postion: Int) {
                Toast.makeText(context, "--"+postion, Toast.LENGTH_SHORT).show()
            }
        })

        myadapter.setOnItemClick {
            Toast.makeText(context, "--" + it, Toast.LENGTH_SHORT).show()
            Log.e("---", "--------${it.toString()}--")
        }
    }


    override fun onObersverData() {
         vModel.getArticleData().observe(this, Observer {

             lifecycleScope.launchWhenCreated {
                 articleAdapter.submitData(it)
             }

         })


        //监听刷新状态当刷新完成之后关闭刷新
        lifecycleScope.launchWhenCreated {
            @OptIn(ExperimentalCoroutinesApi::class)
            articleAdapter.loadStateFlow.collectLatest {
                if(it.refresh !is LoadState.Loading){
                    rootView.refreshLayout.finishRefreshing()

                }
            }
        }



        vModel.loadstste.observe(this, Observer {
            when (it) {
                State.SUCCESS -> {
                    rootView.loads.setVisibility(View.GONE)
                    rootView.refreshLayout.finishLoadmore()
                    rootView.refreshLayout.finishRefreshing()
                }
                State.LOADING -> rootView.loads.setText("正在加载中--------")
                State.EMPTY -> rootView.loads.setText("--------")
                State.ERRO -> {
                    rootView.refreshLayout.finishRefreshing()
                    rootView.loads.setText("错误加载--------")
                }

                State.EMPTYMORE -> {
                    rootView.refreshLayout.finishLoadmore()
                    Toast.makeText(context, "没有数据了", Toast.LENGTH_SHORT).show()
                }
                State.ERROMORE -> {
                    rootView.refreshLayout.finishLoadmore()
                    Toast.makeText(context, "加载失败", Toast.LENGTH_SHORT).show()
                }

            }
            //  states:State
            Log.e("iiiiiii", "状态的变化====$it")
        })

        vModel.content.observe(this, Observer {
            myadapter.sumit(it)
        })


    }

    override fun startLoad() {

    }

}
