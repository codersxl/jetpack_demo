package com.mooc.koltin_demo_test.paging;

import android.util.Log
import androidx.paging.PagingSource
import com.mooc.koltin_demo_test.api.RetrofitManger
import com.mooc.koltin_demo_test.ben.MapData

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: ArticleDataSource
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/19 0019 17:32
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
class ArticleDataSource :PagingSource<Int,MapData>() {

    /**
     * 实现这个方法来触发异步加载(例如从数据库或网络)。 这是一个suspend挂起函数，可以很方便的使用协程异步加载
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MapData> {

        return try {

            val page = params.key?:0
            Log.e("========",page.toString()+"----------页数--------------")
            //获取网络数据
            val result = RetrofitManger.Api.getHome(page)
            LoadResult.Page(
                //需要加载的数据
                data = result.data.tbk_dg_optimus_material_response.result_list?.map_data?: emptyList(),
                //如果可以往上加载更多就设置该参数，否则不设置if(page==0) null else page-1
                prevKey = null,
                //加载下一页的key 如果传null就说明到底了
                nextKey = if(result.data.tbk_dg_optimus_material_response==null) null else page+1

                        //endOfPaginationReached=false
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }

    }
}
