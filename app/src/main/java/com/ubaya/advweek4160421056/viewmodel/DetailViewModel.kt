package com.ubaya.advweek4160421056.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.advweek4160421056.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application){
    val studentLD = MutableLiveData<Student>()
    fun refresh(student_ID:String){
        val TAG = "volleyTag" //ini bebas mau dikasi nama apa variablenya
        var queue: RequestQueue?=null //object volley
        queue = Volley.newRequestQueue(getApplication())//requestQueue butuh context, karena viewmodel gada context maka parent class diganti AndroidViewModel
        val url = "http://adv.jitusolution.com/student.php?id=" + student_ID
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            {
                Log.d("show_volley_detail",it)
                //stype object untuk GSON
                //mengirim data tunggal berupa class student
                val student1 = Gson().fromJson(it,Student::class.java)
                studentLD.value = student1
            },
            {
                Log.e("show_volley",it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}