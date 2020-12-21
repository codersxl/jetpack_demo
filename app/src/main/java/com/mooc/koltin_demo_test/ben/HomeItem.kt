package com.mooc.koltin_demo_test.ben;

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: HomeItem
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/17 0017 16:10
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
data class HomeItem(
    val tbk_dg_optimus_material_response: TbkDgOptimusMaterialResponse
){
    data class TbkDgOptimusMaterialResponse(
        val is_default: String,
        val request_id: String,
        val result_list: ResultList
    ){
        data class ResultList(
            val map_data: MutableList<MapData>
        )
    }
}





