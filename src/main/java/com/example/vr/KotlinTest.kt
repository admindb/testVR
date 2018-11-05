package com.example.vr

import android.util.Log
import java.io.File
import java.nio.charset.Charset
import java.sql.DriverManager.println

/**
 * Created by Administrator on 2018/7/10.
 */

object KotlinTest{
    @JvmStatic
    fun sayMessage(msg:String?){
        Log.e("===","$msg")

        val age=18
        val name="lilili"
        Log.e("===","我叫$name，今年$age")
    }
}

object Utils{
    @JvmStatic
    fun sayMessage(msg:String?){
        Log.e("===","$msg")
        val file=File("vr.iml")
//        Log.e("===",file.readText())
    }
}

fun File.readText(charset:Charset=Charsets.UTF_8):String = readBytes().toString(charset)



