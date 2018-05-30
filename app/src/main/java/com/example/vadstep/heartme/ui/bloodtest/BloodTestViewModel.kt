package com.example.vadstep.heartme.ui.bloodtest

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.vadstep.heartme.R
import com.example.vadstep.heartme.model.Config
import com.example.vadstep.heartme.model.Result
import com.example.vadstep.heartme.model.cleanString
import com.example.vadstep.heartme.model.isTest


class BloodTestViewModel : ViewModel() {
    var mCurrentName: MutableLiveData<Result> = MutableLiveData()
    var name: String = ""
    var test: String = ""
    lateinit var model: Config
    fun calc() {
        for (x in model.bloodTestConfig) {
            for (y in name.cleanString()) {
                if (test.toIntOrNull() != null) {
                    if (y.isTest(x.name.cleanString())) {
                        if (test.toInt() <= x.threshold) {
                            mCurrentName.value = Result(x.name + "\nGood!", R.drawable.smile)
                        } else {
                            mCurrentName.value = Result(x.name + "\nBad!", R.drawable.sad)
                        }
                        return
                    } else {
                        mCurrentName.value = Result("Unknown", 0)
                    }

                } else {
                    mCurrentName.value = null
                }
            }
        }
    }
}
