package com.jalak.testandroid.model

import android.widget.Toast
import com.jalak.testandroid.entities.ItemDataItem
import com.jalak.testandroid.network.ItemService
import com.jalak.testandroid.network.RestEngine
import com.jalak.testandroid.presenter.ItemPresenterImpl
import com.jalak.testandroid.util.EncryptText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemInteractorImpl(presenter: ItemPresenterImpl) : ItemInteractor {
  var presenter: ItemPresenterImpl? = presenter
  override fun getItem() {
    val encrypt: EncryptText = EncryptText()
    val text = encrypt.encryptRut("1-9").replace("\n", "")
    val itemService: ItemService = RestEngine.getRestEngineItems()
      .create(ItemService::class.java)
    val result: Call<ItemDataItem> = itemService.getItem(text)

    result.enqueue(object : Callback<ItemDataItem> {
      override fun onFailure(call: Call<ItemDataItem>, t: Throwable) {
        presenter?.errorItemResult(t.message.toString())
      }

      override fun onResponse(call: Call<ItemDataItem>, response: Response<ItemDataItem>) {
        response.body()?.result?.items?.get(1)?.let { presenter?.showItemResult(it) }
      }
    })
  }
}