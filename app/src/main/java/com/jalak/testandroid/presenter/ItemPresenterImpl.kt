package com.jalak.testandroid.presenter

import com.jalak.testandroid.entities.Detail
import com.jalak.testandroid.entities.Item
import com.jalak.testandroid.model.ItemInteractorImpl
import com.jalak.testandroid.view.MainActivity

class ItemPresenterImpl(view: MainActivity) : ItemPresenter {
  var view: MainActivity? = view
  var interactor: ItemInteractorImpl = ItemInteractorImpl(this)

  override fun getItem(rut: String) {
    interactor.getItem(rut)
  }

  override fun setUser(user: Detail) {
    interactor.setUser(user)
  }

  override fun showItemResult(result: Item) {
    view?.showItemResult(result)
  }

  override fun showUsuarioResult(id: String) {
    view?.showUserResult(id)
  }

  override fun errorItemResult(message: String) {
    view?.errorItemResult(message)
  }
}