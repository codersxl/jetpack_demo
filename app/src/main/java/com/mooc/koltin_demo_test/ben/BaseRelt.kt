package com.mooc.koltin_demo_test.ben;

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: BaseRelt
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/17 0017 16:46
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
data class BaseRelt<T>( val code: Int,
                     val data: T,
                     val message: String,
                     val success: Boolean)
