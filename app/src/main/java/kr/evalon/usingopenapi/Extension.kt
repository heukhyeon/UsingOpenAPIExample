package kr.evalon.usingopenapi

import com.google.gson.Gson
import com.google.gson.GsonBuilder


fun Any.toJson():String{
    return GsonBuilder().setPrettyPrinting().create().toJson(this)
}