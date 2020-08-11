package com.jalak.testandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jalak.testandroid.*
import com.jalak.testandroid.entities.ItemDataItem
import com.jalak.testandroid.entities.UserDataItem
import com.jalak.testandroid.network.ItemService
import com.jalak.testandroid.network.RestEngine
import com.jalak.testandroid.network.UserService
import com.jalak.testandroid.util.EncryptText
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btn_test.setOnClickListener { callServiceGetItem() }
  }

  private fun callServiceGetUsers() {
    val userService: UserService = RestEngine.getRestEngine()
      .create(UserService::class.java)
    val result: Call<List<UserDataItem>> = userService.getUsers()

    result.enqueue(object : Callback<List<UserDataItem>> {
      override fun onFailure(call: Call<List<UserDataItem>>, t: Throwable) {
        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
      }

      override fun onResponse(
        call: Call<List<UserDataItem>>, response: Response<List<UserDataItem>>
      ) {
        Toast.makeText(this@MainActivity, "Ok", Toast.LENGTH_SHORT).show()
      }
    })
  }

  private fun callServiceGetItem() {
    val encrypt: EncryptText =
      EncryptText()
    val text = encrypt.encryptRut("1-9").replace("\n", "")
    val itemService: ItemService = RestEngine.getRestEngineItems()
      .create(ItemService::class.java)
    val result: Call<ItemDataItem> = itemService.getItem(text)

    result.enqueue(object : Callback<ItemDataItem> {
      override fun onFailure(call: Call<ItemDataItem>, t: Throwable) {
        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
      }

      override fun onResponse(call: Call<ItemDataItem>, response: Response<ItemDataItem>) {
        Toast.makeText(
          this@MainActivity,
          "Ok ${response.body()!!.result!!.items[1]}",
          Toast.LENGTH_SHORT
        ).show()
      }
    })
  }
}