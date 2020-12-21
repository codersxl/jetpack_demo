package com.mooc.koltin_demo_test.lifecycle;

import android.util.Log
import androidx.lifecycle.*

/**
 *Copyright (C), 2015-2020, XXX有限公司
 * @ClassName: Mylifecycle
 * @Description: java类作用描述
 * @Author: xueliang
 * @Date: 2020/12/21 0021 18:46
 *Description:
 *博客地址：https://blog.csdn.net/xueshao110?spm=1010.2135.3001.5113
 */
class Mylifecycle private constructor(): DefaultLifecycleObserver {

     companion object{
         val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
              Mylifecycle()
         }
     }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun create() {
        Log.d("~~~", "ON_CREATE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.d("~~~", "ON_START")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.d("~~~", "ON_RESUME")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.d("~~~", "ON_PAUSE")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.d("~~~", "ON_STOP")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        Log.d("~~~", "ON_DESTROY")
    }

//    override fun onCreate(owner: LifecycleOwner) {
//        super.onCreate(owner)
//        Log.d("~~~", "onCreate")
//    }
//    override fun onResume(owner: LifecycleOwner) {
//        super.onResume(owner)
//        Log.d("~~~", "onResume")
//    }
//
//    override fun onPause(owner: LifecycleOwner) {
//        super.onPause(owner)
//        Log.d("~~~", "onPause")
//    }
//
//    override fun onStart(owner: LifecycleOwner) {
//        super.onStart(owner)
//        Log.d("~~~", "onStart")
//    }
//
//    override fun onStop(owner: LifecycleOwner) {
//        super.onStop(owner)
//        Log.d("~~~", "onStop")
//    }
//
//    override fun onDestroy(owner: LifecycleOwner) {
//        super.onDestroy(owner)
//        Log.d("~~~", "onDestroy")
//    }
}
