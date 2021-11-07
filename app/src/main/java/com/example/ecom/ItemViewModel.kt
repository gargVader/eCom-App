package com.example.ecom

import android.app.Application
import androidx.lifecycle.*
import com.example.ecom.data.Item
import com.example.ecom.data.ItemRepository
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val itemRepository = ItemRepository(application)

    val allItemList: LiveData<List<Item>> = itemRepository.allItemList

    fun getSearchItems(searchKeyword: String): LiveData<List<Item>> {
        return itemRepository.getSearchItems("%"+searchKeyword+"%")
    }

    fun insert(item: Item) = viewModelScope.launch {
        itemRepository.insert(item)
    }

    fun insert(itemList: List<Item>) = viewModelScope.launch {
        itemRepository.insert(itemList)
    }

    fun update(item: Item) = viewModelScope.launch {
        itemRepository.update(item)
    }

    fun delete(item: Item) = viewModelScope.launch {
        itemRepository.delete(item)
    }

    fun deleteAll() = viewModelScope.launch {
        itemRepository.deleteAll()
    }

}