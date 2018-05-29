package com.example.vadstep.heartme.ui.bloodtest

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vadstep.heartme.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.blood_test_fragment.*



class BloodTestFragment : Fragment() {

    companion object {
        fun newInstance() = BloodTestFragment()
    }

    private lateinit var viewModel: BloodTestViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.blood_test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        test.visibility = View.INVISIBLE
        viewModel = ViewModelProviders.of(this).get(BloodTestViewModel::class.java)
        getAPI()
        viewModel.mCurrentName.observe(this, Observer<String> {
            if (it.isNullOrEmpty()) {
                test.visibility = View.INVISIBLE
            } else {
                test.visibility = View.VISIBLE
            }
        })
        name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.name = p0.toString()
            }
        })
        result.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.test = p0.toString()
                viewModel.calc()
            }
        })

    }

    private fun getAPI() {
        val queue = Volley.newRequestQueue(context)
        val url = "https://s3.amazonaws.com/s3.helloheart.home.assignment/bloodTestConfig.json"
        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    viewModel.model=Gson().fromJson(response,Config::class.java)
                },
                Response.ErrorListener {
                    viewModel.model=Config(bloodTestConfig= listOf(Test(name="HDL Cholesterol", threshold=40), Test(name="LDL Cholesterol", threshold=100), Test(name="A1C", threshold=4)))
                })

        queue.add(stringRequest)
    }

}
