package com.jalak.testandroid.presenter

import com.jalak.testandroid.entities.Detail
import com.jalak.testandroid.entities.Item
import com.jalak.testandroid.entities.ItemDataItem

interface ItemPresenter {

  fun getItem(rut:String)
  fun setUser(user:Detail)
  fun showItemResult(result: Item)
  fun showUsuarioResult(id: String)
  fun errorItemResult(message: String)
}