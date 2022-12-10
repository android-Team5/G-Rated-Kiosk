package com.example.g_rated_kiosk.DataManage

import android.content.Context
import android.os.Environment
import android.util.Log
import org.json.JSONArray
import java.io.*


class FileManager {
    companion object{
        fun write(json:JSONArray, context: Context){
            val fileTitle = "test.json"
            val file = File(context.cacheDir, "data.json")
            try {
                if (!file.exists()) {
                    file.createNewFile()
                }

                val bw = BufferedWriter(FileWriter(file))
                bw.write(json.toString())
                bw.newLine()
                bw.close()
            } catch (e: IOException) {
                Log.i("저장오류", e.stackTraceToString())
            }
        }

        fun read(context:Context):JSONArray{
            val file = File(context.cacheDir,"data.json")
            var json:JSONArray = JSONArray()
            try {
                val reader = BufferedReader(FileReader(file))
                var result: String? = ""
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    result += line
                }
                json = JSONArray(result)
                reader.close()
            } catch (e1: FileNotFoundException) {
                Log.i("파일못찾음", e1.stackTraceToString())
            } catch (e2: IOException) {
                Log.i("읽기오류", e2.stackTraceToString())
            }
            return json
        }
    }

}