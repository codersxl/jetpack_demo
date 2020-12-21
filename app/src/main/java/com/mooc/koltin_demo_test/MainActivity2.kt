package com.mooc.koltin_demo_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mooc.koltin_demo_test.databinding.ActivityMain2Binding
import com.mooc.koltin_demo_test.fragment.HomeFragment
import com.mooc.koltin_demo_test.lifecycle.Mylifecycle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        val bind: ActivityMain2Binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main2)
        val beginTransaction = supportFragmentManager.beginTransaction();
          beginTransaction.add(HomeFragment(),"")
              .commit()
        lifecycle.addObserver(Mylifecycle.instance)
    }
}