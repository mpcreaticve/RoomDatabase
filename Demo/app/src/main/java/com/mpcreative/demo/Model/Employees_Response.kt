package com.mpcreative.demo.Model


import com.google.gson.annotations.SerializedName

data class Employees_Response(
    @SerializedName("data")
    var `data`: ArrayList<Data>,
    @SerializedName("status")
    var status: String
)