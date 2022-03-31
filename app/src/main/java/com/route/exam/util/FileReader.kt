package com.route.exam.util

import android.content.Context
import android.system.Os.open
import android.util.Log
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.nio.channels.FileChannel.open


class FileReader {
        companion object
        {
                fun  readFile(context: Context,filename:String):String?
                {
                    lateinit var jsonString: String
                    try {
                        jsonString = context.assets.open(filename)
                            .bufferedReader()
                            .use {
                                it.readText()
                            }
                        return jsonString
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    return  null;

                }
        }
}