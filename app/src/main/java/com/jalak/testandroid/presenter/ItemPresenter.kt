package com.jalak.testandroid.presenter

import com.jalak.testandroid.entities.Item
import com.jalak.testandroid.entities.ItemDataItem

interface ItemPresenter {

  fun getItem()
  fun showItemResult(result: Item)
  fun errorItemResult(message: String)
}