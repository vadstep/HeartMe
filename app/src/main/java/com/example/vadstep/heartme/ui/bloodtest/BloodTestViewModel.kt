package com.example.vadstep.heartme.ui.bloodtest

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel



class BloodTestViewModel : ViewModel() {
    var mCurrentName: MutableLiveData<String> =MutableLiveData()
    var name: String=""
    var test: String=""
    lateinit var model: Config
    fun calc(){
        if(test.length>4){
            mCurrentName.value="hui"
        }else{
            mCurrentName.value=""
        }
    }

}
