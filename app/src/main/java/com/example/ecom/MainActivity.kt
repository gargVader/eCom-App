package com.example.ecom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecom.data.Item

class MainActivity : AppCompatActivity() {

    val TAG = "Ecom"
    var mItemList: List<Item> = listOf()
    private var itemViewModel: ItemViewModel? = null
    private var itemAdapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Setup recyclerView
        itemAdapter = ItemAdapter(mItemList, this)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        initViewModel()
    }

    fun initViewModel() {
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
//        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
//        itemViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
//        ).get(ItemViewModel::class.java)
        itemViewModel!!.getSearchItems("mango").observe(this, object : Observer<List<Item>> {
            override fun onChanged(itemList: List<Item>) {
                mItemList = itemList
                itemAdapter?.setData(mItemList)
            }
        })
    }


//    private fun loadJSONData(): List<Item> {
//        val jsonString = loadJSONString(this)
//        val mGson = Gson()
//        val sType = object : TypeToken<List<Item>>() {}.type
//        return mGson.fromJson(jsonString, sType)
//    }
//
//    private fun loadJSONString(context: Context): String {
//        val inputStream: InputStream = context.resources.openRawResource(R.raw.list)
//        return inputStream.bufferedReader().use { it.readText() }
//    }


}