package com.mooc.koltin_demo_test.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mooc.koltin_demo_test.api.RetrofitManger
import com.mooc.koltin_demo_test.paging.ArticleDataSource

class Repository {
      /**
       * 请求数据
       *
       */
      suspend fun getHomes(page:Int)=RetrofitManger.Api.getHome(page)

      fun getArticleData() = Pager(PagingConfig(pageSize = 20)){
            ArticleDataSource()
      }.flow
}