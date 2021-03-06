package com.jalak.testandroid.view

import com.jalak.testandroid.entities.Item
import com.jalak.testandroid.entities.ItemDataItem

interface MainView {

  fun showItemResult(result: Item)
  fun showUserResult(id: String)
  fun errorItemResult(message: String)
}