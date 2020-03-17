package com.mpcreative.demo.Reset

import com.mpcreative.demo.Model.Employees_Response
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("employees")
    fun getEmployee(): retrofit2.Call<Employees_Response>


}