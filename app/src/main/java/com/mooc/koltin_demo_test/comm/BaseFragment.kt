package com.mooc.koltin_demo_test.comm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    protected lateinit var rootView: T
    protected lateinit var rootViews: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = DataBindingUtil.inflate<T>(inflater, getLayout(), container, false);
        rootViews = rootView.root
        return rootViews
    }

    public abstract fun getLayout(): Int

}