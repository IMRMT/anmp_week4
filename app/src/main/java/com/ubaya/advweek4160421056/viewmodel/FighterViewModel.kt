package com.ubaya.advweek4160421056.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4160421056.model.Fighter
import com.ubaya.advweek4160421056.model.Student

class FighterViewModel(application: Application):AndroidViewModel(application) {
    val fightersLD = MutableLiveData<ArrayList<Fighter>>()
    val fighterLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    fun refresh() {

        Log.d("CEKMASUK","masukvolley")
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://10.0.2.2/fighters/fighters.json"
        val stringRequest = StringRequest(Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Fighter>>() {}.type
                val result = Gson().fromJson<List<Fighter>>(it,sType)
                fightersLD.value = result as ArrayList<Fighter>?
                Log.d("showvolley",result.toString())
            },
            {
                Log.d("showvolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}