package com.example.vadstep.heartme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.vadstep.heartme.ui.bloodtest.BloodTestFragment

class BloodTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blood_test_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, BloodTestFragment.newInstance())
                    .commitNow()
        }
    }

}
