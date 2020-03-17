package com.mpcreative.demo.Model


import com.google.gson.annotations.SerializedName

 class Data(
    @SerializedName("employee_age")
    var employeeAge: String,
    @SerializedName("employee_name")
    var employeeName: String,
    @SerializedName("employee_salary")
    var employeeSalary: String,
    @SerializedName("id")
    var id: String,
    @SerializedName("profile_image")
    var profileImage: String
)