package com.mooc.koltin_demo_test.comm

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


abstract class BaseVmFragment<T : ViewDataBinding, V : ViewModel> : BaseFragment<T>() {

    lateinit var vModel: V
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intViewMolde()
        initView()
        startLoad()
        onObersverData()
        intEvent()

    }

    open fun initView() {

    }

    open fun onObersverData() {

    }

    open fun startLoad() {

    }

    open fun intEvent() {}

    fun intViewMolde() {
        vModel = ViewModelProvider.NewInstanceFactory().create(getClasss())
        rootView.lifecycleOwner=this
    }

    abstract fun getClasss(): Class<V>

}