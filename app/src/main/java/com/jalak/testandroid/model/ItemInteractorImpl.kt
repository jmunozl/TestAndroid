package com.jalak.testandroid.model

import android.util.Log
import android.widget.Toast
import com.jalak.testandroid.entities.Detail
import com.jalak.testandroid.entities.DetailUser
import com.jalak.testandroid.entities.ItemDataItem
import com.jalak.testandroid.entities.UserDataItem
import com.jalak.testandroid.network.ItemService
import com.jalak.testandroid.network.RestEngine
import com.jalak.testandroid.network.UserService
import com.jalak.testandroid.presenter.ItemPresenterImpl
import com.jalak.testandroid.util.EncryptText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemInteractorImpl(presenter: ItemPresenterImpl) : ItemInteractor {
  var presenter: ItemPresenterImpl? = presenter
  override fun getItem(rut:String) {
    val encrypt: EncryptText = EncryptText()
    val text = encrypt.encryptRut(rut).replace("\n", "")
    val itemService: ItemService = RestEngine.getRestEngineItems()
      .create(ItemService::class.java)
    val result: Call<ItemDataItem> = itemService.getItem(text)

    result.enqueue(object : Callback<ItemDataItem> {
      override fun onFailure(call: Call<ItemDataItem>, t: Throwable) {
        presenter?.errorItemResult(t.message.toString())
      }

      override fun onResponse(call: Call<ItemDataItem>, response: Response<ItemDataItem>) {
        val count = response.body()?.result?.items?.count();
        if (count!! >0){
          response.body()?.result?.items?.get(1)?.let { presenter?.showItemResult(it) }
        }else{
          presenter?.errorItemResult("Item no encontrado")
        }
      }
    })
  }

  override fun setUser(user: Detail) {
    val userService: UserService = RestEngine.getRestEngine().create(UserService::class.java)
    val result: Call<DetailUser> = userService.setUsers(user)

    result.enqueue(object : Callback<DetailUser> {
      override fun onFailure(call: Call<DetailUser>, t: Throwable) {
        presenter?.errorItemResult(t.message.toString())
      }

      override fun onResponse(
        call: Call<DetailUser>, response: Response<DetailUser>
      ) {
        val id = response.body()?.id.toString()
        if (!id?.isEmpty()) {
          id.let { it?.let { it1 -> presenter?.showUsuarioResult(it1) } }
        } else {
          presenter?.errorItemResult("User no encontrado")
        }
        Log.i("TAG", "onResponse: ${response.body()}")
      }
    })
  }
}