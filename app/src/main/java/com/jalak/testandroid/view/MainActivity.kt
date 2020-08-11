package com.jalak.testandroid.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jalak.testandroid.*
import com.jalak.testandroid.entities.Detail
import com.jalak.testandroid.entities.Item
import com.jalak.testandroid.entities.ItemDataItem
import com.jalak.testandroid.entities.UserDataItem
import com.jalak.testandroid.network.ItemService
import com.jalak.testandroid.network.RestEngine
import com.jalak.testandroid.network.UserService
import com.jalak.testandroid.presenter.ItemPresenterImpl
import com.jalak.testandroid.util.EncryptText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.response_rut_dialog.*
import kotlinx.android.synthetic.main.response_rut_dialog.view.*
import kotlinx.android.synthetic.main.response_user_dialog.view.*
import kotlinx.android.synthetic.main.search_rut_dialog.*
import kotlinx.android.synthetic.main.search_rut_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), MainView {

  var itemPresenterImpl: ItemPresenterImpl = ItemPresenterImpl(this)
  var user: Detail? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    btn_aler1.setOnClickListener { buildDialog() }
    btn_aler2.setOnClickListener { callServiceSetUser(user) }

  }


  private fun callServiceSetUser(user: Detail?) {
    if (user ==null){
      errorItemResult("Debe consumir servicio item")
    }else{
      itemPresenterImpl.setUser(user!!)
    }
  }

  private fun callServiceGetItem(rut: String) {
    itemPresenterImpl.getItem(rut)
  }

  override fun showItemResult(result: Item) {
    user = buildDialogItemResponse(result)
  }

  override fun showUserResult(id: String) {
    buildDialogUserResponse(id)
  }

  override fun errorItemResult(message: String) {
    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
  }

  private fun buildDialog() {

    val dialogView = LayoutInflater.from(this).inflate(R.layout.search_rut_dialog, null)
    val builder = AlertDialog.Builder(this).setView(dialogView).setTitle("Search item")
    val alertDialog = builder.show()

    dialogView.btn_search.setOnClickListener {
      alertDialog.dismiss()
      val rut = dialogView.et_rut.text.toString()
      if (!rut.isEmpty()) {
        callServiceGetItem(rut)
      } else {
        errorItemResult("Debe ingresar un rut")
      }
    }

    dialogView.btn_cancel.setOnClickListener { alertDialog.dismiss() }

  }

  private fun buildDialogItemResponse(item: Item): Detail {

    val dialogView = LayoutInflater.from(this).inflate(R.layout.response_rut_dialog, null)
    val builder = AlertDialog.Builder(this).setView(dialogView).setTitle("Response item")
    val alertDialog = builder.show()

    dialogView.et_email.setText(item.detail.email)
    dialogView.et_phone_number.setText(item.detail.phoneNumber)
    dialogView.btn_close.setOnClickListener { alertDialog.dismiss() }

    return item.detail

  }

  private fun buildDialogUserResponse(id: String) {

    val dialogView = LayoutInflater.from(this).inflate(R.layout.response_user_dialog, null)
    val builder = AlertDialog.Builder(this).setView(dialogView).setTitle("Response user")
    val alertDialog = builder.show()
    dialogView.et_id.setText(id)
    dialogView.btn_close_user.setOnClickListener { alertDialog.dismiss() }
  }


}