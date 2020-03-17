package com.mpcreative.demo

import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpcreative.demo.Adapter.EmployeeAdapter
import com.mpcreative.demo.Model.Data
import com.mpcreative.demo.Model.Employees_Response
import com.mpcreative.demo.Reset.ApiClient
import com.mpcreative.demo.Reset.ApiInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

    var arrayList: ArrayList<Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("Tag", "onCreate")

        calApi()
        addUserDetail()
    }

    private fun addUserDetail() {
       /* var strId = FirebaseInstanceId.getInstance().token
        val database = f.getInstance()
        val myRef = database.getReference("User")
        var android_id = Settings.Secure.getString(
            this.contentResolver,
            Settings.Secure.ANDROID_ID
        )*/
    }


    private fun calApi() {
        val apiInterface = ApiClient.getRetrofit()!!.create(ApiInterface::class.java)
        val call = apiInterface.getEmployee()
        rv_employee.layoutManager = LinearLayoutManager(this)
        // rv_employee.layoutManager = GridLayoutManager(this, 2)

        call.enqueue(object : Callback<Employees_Response> {
            override fun onFailure(call: Call<Employees_Response>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<Employees_Response>,
                response: Response<Employees_Response>
            ) {
                if (response.isSuccessful) {

                    val data = response.body()!!.data

                    val adapter = EmployeeAdapter(this@MainActivity, data)
                    rv_employee.adapter = adapter
                }
            }
        })
    }


    override fun onStart() {
        super.onStart()
        Log.e("Tag", "onStart")

    }

    override fun onResume() {
        super.onResume()
        Log.e("Tag", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Tag", "onRestart")

    }

    override fun onPause() {
        super.onPause()
        Log.e("Tag", "onPause")

    }




}
