package com.mooc.koltin_demo_test.livedata

import androidx.lifecycle.*
import java.util.concurrent.ConcurrentHashMap

class LiveDataBus {

    private object Lazy {
        var sLiveDataBus = LiveDataBus()
    }

    private val mHashMap = ConcurrentHashMap<String, StickyLiveData<*>>()
    fun with(eventName: String): StickyLiveData<*> {
        var liveData = mHashMap[eventName]
        if (liveData == null) {
            liveData = StickyLiveData<Any?>(eventName)
            mHashMap[eventName] = liveData
        }
        return liveData
    }

    /**
     * 实际上liveData黏性事件总线的实现方式 还有另外一套实现方式。
     * 一堆反射 获取LiveData的mVersion字段，来控制数据的分发与否，不够优雅。
     *
     *
     * 但实际上 是不需要那么干的。请看我们下面的实现方式。
     *
     * @param <T>
    </T> */
    inner class StickyLiveData<T>(private val mEventName: String) : LiveData<T?>() {
        private var mStickyData: T? = null
        private var mVersion = 0
        public override fun setValue(value: T?) {
            mVersion++
            super.setValue(value)
        }

        public override fun postValue(value: T?) {
            mVersion++
            super.postValue(value)
        }

        fun setStickyData(stickyData: T) {
            mStickyData = stickyData
            value = stickyData
        }

        fun postStickyData(stickyData: T) {
            mStickyData = stickyData
            postValue(stickyData)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<in T?>) {
            observerSticky(owner, observer, false)
        }

        fun observerSticky(owner: LifecycleOwner, observer: Observer<in T?>, sticky: Boolean) {
           // super.observe(owner, WrapperObserver<Any?>(this, observer, sticky))
            owner.lifecycle.addObserver(LifecycleEventObserver { source, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    mHashMap.remove(mEventName)
                }
            })
        }

        private inner class WrapperObserver<T>(liveData: StickyLiveData<*>, observer: Observer<in T?>, sticky: Boolean) : Observer<T> {
            private val mLiveData: StickyLiveData<T?>
            private val mObserver: Observer<T?>
            private val mSticky: Boolean

            //标记该liveData已经发射几次数据了，用以过滤老数据重复接收
            private var mLastVersion = 0
            override fun onChanged(t: T) {
                //如果当前observer收到数据的次数已经大于等于了StickyLiveData发送数据的个数了则return
                /**
                 * observer.mLastVersion >= mLiveData.mVersion
                 * 这种情况 只会出现在，我们先行创建一个liveData发射了一条数据。此时liveData的mversion=1.
                 *
                 * 而后注册一个observer进去。由于我们代理了传递进来的observer,进而包装成wrapperObserver，此时wrapperObserver的lastVersion 就会跟liveData的mversion 对齐。保持一样。把wrapperObserver注册到liveData中。
                 *
                 * 根据liveData的原理，一旦一个新的observer 注册进去,也是会尝试把数据派发给他的。这就是黏性事件(先发送,后接收)。
                 *
                 * 但此时wrapperObserver的lastVersion 已经和 liveData的version 一样了。由此来控制黏性事件的分发与否
                 */
                if (mLastVersion >= mLiveData.mVersion) {
                    //但如果当前observer它是关心 黏性事件的，则给他。
                    if (mSticky && mLiveData.mStickyData != null) {
                        mObserver.onChanged(mLiveData.mStickyData)
                    }
                    return
                }
                mLastVersion = mLiveData.mVersion
                mObserver.onChanged(t)
            }

            init {
                mLiveData = liveData as StickyLiveData<T?>
                mObserver = observer as Observer<T?>
                mSticky = sticky

                //比如先使用StickyLiveData发送了一条数据。StickyLiveData#version=1
                //那当我们创建WrapperObserver注册进去的时候，就至少需要把它的version和 StickyLiveData的version保持一致
                //用以过滤老数据，否则 岂不是会收到老的数据？
                mLastVersion = mLiveData.mVersion
            }
        }
    }

    companion object {
        fun get(): LiveDataBus {
            return Lazy.sLiveDataBus
        }
    }
}