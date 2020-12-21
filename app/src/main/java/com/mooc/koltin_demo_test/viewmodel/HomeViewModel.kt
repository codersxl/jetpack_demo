package com.mooc.koltin_demo_test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mooc.koltin_demo_test.ben.BaseRelt

import com.mooc.koltin_demo_test.ben.HomeItem
import com.mooc.koltin_demo_test.ben.MapData
import com.mooc.koltin_demo_test.repository.Repository
import com.mooc.koltin_demo_test.utils.State
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.NullPointerException

class HomeViewModel : ViewModel() {
    var isresh:Boolean =false
    private val home: Repository
            by lazy {
                Repository()
            }

    companion object {
        const val DEF = 1
    }

    var currntPage :Int= DEF

    fun loadata() {
        this.isresh=false
        loadstste.value = State.LOADING
        loadDataById(currntPage)
    }

    private fun loadDataById(currntPages: Int) {
        viewModelScope.launch {
            try {

                val homes: BaseRelt<HomeItem> = home.getHomes(currntPage)
                val mapData: MutableList<MapData> = homes.data.tbk_dg_optimus_material_response.result_list.map_data
                val olddata:MutableList<MapData> =content.value?: mutableListOf()
                 olddata.addAll(mapData)
                if (mapData.isEmpty()) {
                    if(isresh)  loadstste.value = State.EMPTYMORE else loadstste.value = State.EMPTY

                } else {

                    loadstste.value = State.SUCCESS
                    content.value = olddata
                }
            } catch (e: Exception) {
                currntPage --

                if(e is NullPointerException){
                    loadstste.value = State.ERROMORE
                }else{
                    if(isresh)loadstste.value = State.ERROMORE else loadstste.value = State.ERRO
                }

            }
        }
    }

    fun loadMore() {
        loadstste.value = State.LOADINGMORE
        this.isresh=true
        currntPage++
        loadDataById(currntPage)
    }

    val loadstste by lazy {
        MutableLiveData<State>()
    }

    val content by lazy {
        MutableLiveData<MutableList<MapData>>()
    }


    /**
     * Pager 分页入口 每个PagingData代表一页数据 最后调用asLiveData将结果转化为一个可监听的LiveData
     */
    fun getArticleData() = home.getArticleData().asLiveData()

}

