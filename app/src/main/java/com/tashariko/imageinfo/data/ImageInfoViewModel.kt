package com.tashariko.imageinfo.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import java.io.IOException

//
class ImageInfoViewModel : ViewModel() {

    private val _tempGameListLiveData = MutableLiveData<List<ImageInfo>>()

    val imgListLiveData: LiveData<List<ImageInfo>>
        get() = _tempGameListLiveData

    fun getData(context: Context) {
         viewModelScope.launch {
            updateList(context)
        }
    }

    private fun updateList(context: Context) {
        val jsonString = loadJSONFromAsset(context)
        val gson = Gson()
        val jsonObject = gson.fromJson(jsonString, JsonObject::class.java)

        val imgList = ArrayList<ImageInfo>()

        for (jsonElement in jsonObject.get("TestData").asJsonArray){
            val jObject = jsonElement.asJsonObject
            val imgInfo = gson.fromJson(jObject,
                ImageInfo::class.java)
            imgList.add(imgInfo)
        }

        imgList.addAll(imgList)
        imgList.addAll(imgList)

        _tempGameListLiveData.value = imgList

    }

    private fun loadJSONFromAsset(mContext: Context): String {
        lateinit var json: String
        json = try {
            val jsonStream = mContext.assets.open("test_result.json")
            val size = jsonStream.available()
            val buffer = ByteArray(size)
            jsonStream.read(buffer)
            jsonStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return "{}"
        }
        return json
    }

}
