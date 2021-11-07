package com.example.ecom

import `in`.slanglabs.assistants.retail.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecom.data.Item

class MainActivity : AppCompatActivity(), SlangRetailAssistant.Action,
    SearchView.OnQueryTextListener {

    val TAG = "Ecom"
    var mItemList: List<Item> = listOf()
    private var itemViewModel: ItemViewModel? = null
    private var itemAdapter: ItemAdapter? = null
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)


        // Setup recyclerView
        itemAdapter = ItemAdapter(mItemList, this)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        initViewModel()

        searchView.setOnQueryTextListener(this)
        SlangRetailAssistant.setAction(this)
    }

    override fun onResume() {
        super.onResume()
        SlangRetailAssistant.getUI().showTrigger(this)
    }

    private fun initViewModel() {
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        itemViewModel!!.allItemList.observe(this, object : Observer<List<Item>> {
            override fun onChanged(itemList: List<Item>) {
                mItemList = itemList
                itemAdapter?.setData(mItemList)
            }
        })
    }

    override fun onSearch(
        searchInfo: SearchInfo?,
        searchUserJourney: SearchUserJourney?
    ): SearchUserJourney.AppState {
        val searchString = searchInfo?.item?.description
        Log.d(TAG, "onSearch: "+searchString)
        if (searchString != null) {
            searchView.setQuery(searchString.trim(), true)
        }
        searchUserJourney?.setSuccess()
        return SearchUserJourney.AppState.SEARCH_RESULTS
    }

    override fun onOrderManagement(
        p0: OrderInfo?,
        p1: OrderManagementUserJourney?
    ): OrderManagementUserJourney.AppState {
        return OrderManagementUserJourney.AppState.UNSUPPORTED
    }

    override fun onNavigation(
        p0: NavigationInfo?,
        p1: NavigationUserJourney?
    ): NavigationUserJourney.AppState {
        return NavigationUserJourney.AppState.UNSUPPORTED
    }

    override fun onAssistantError(p0: AssistantError?) {

    }

    override fun onQueryTextSubmit(searchString: String?): Boolean {
        if (searchString != null) {
            itemViewModel!!.getSearchItems(searchString)
                .observe(this@MainActivity, object : Observer<List<Item>> {
                    override fun onChanged(itemList: List<Item>) {
                        mItemList = itemList
                        itemAdapter?.setData(mItemList)
                    }
                })
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }


}