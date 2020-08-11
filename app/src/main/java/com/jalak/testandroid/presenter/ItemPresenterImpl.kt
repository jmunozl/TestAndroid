package com.jalak.testandroid.presenter

import com.jalak.testandroid.entities.Item
import com.jalak.testandroid.model.ItemInteractorImpl
import com.jalak.testandroid.view.MainActivity

class ItemPresenterImpl(view: MainActivity) : ItemPresenter {
  var view: MainActivity? = view
  var interactor: ItemInteractorImpl = ItemInteractorImpl(this)

  override fun getItem() {
    interactor.getItem()
  }

  override fun showItemResult(result: Item) {
    view?.showItemResult(result)
  }

  override fun errorItemResult(message: String) {
    view?.errorItemResult(message)
  }
}