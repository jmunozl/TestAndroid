package com.jalak.testandroid.model

import com.jalak.testandroid.entities.Detail

interface ItemInteractor {

  fun getItem(rut:String)
  fun setUser(user:Detail)
}