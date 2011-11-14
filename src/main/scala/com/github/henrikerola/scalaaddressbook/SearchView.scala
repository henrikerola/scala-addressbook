package com.github.henrikerola.scalaaddressbook

import vaadin.scala._
import com.vaadin.demo.tutorial.addressbook.data.PersonContainer
import com.vaadin.ui.Window.Notification
import com.vaadin.demo.tutorial.addressbook.data.SearchFilter

class SearchView(val app: AddressBookApplication)
  extends Panel("Search contacts", 100 pct, 100 pct, style = "view") {

  setContent(new FormLayout())

  val tf = add(new TextField("Search term"))
  val fieldToSearch = add(new NativeSelect("Field to search"))
  val saveSearch = add(new CheckBox("Save search", checked = true, immediate = true, action = e => {
    searchName.setVisible(e.getButton().booleanValue())
  }))
  val searchName = add(new TextField("Search name"))
  val search = add(new Button("Search", _ => performSearch()))

  for (i <- 0 until PersonContainer.NATURAL_COL_ORDER.length) {
    fieldToSearch.addItem(PersonContainer.NATURAL_COL_ORDER(i))
    fieldToSearch.setItemCaption(PersonContainer.NATURAL_COL_ORDER(i),
      PersonContainer.COL_HEADERS_ENGLISH(i));
  }

  fieldToSearch.setValue("lastName")
  fieldToSearch.setNullSelectionAllowed(false)

  def performSearch(): Unit = {
    val searchTerm = tf.getValue.asInstanceOf[String]
    if (searchTerm == null || searchTerm.equals("")) {
      getWindow().showNotification("Search term cannot be empty!",
        Notification.TYPE_WARNING_MESSAGE)
      return
    }

    val searchFilter = new SearchFilter(fieldToSearch.getValue(),
      searchTerm, searchName.getValue.asInstanceOf[String])
    if (saveSearch.booleanValue) {
      if (searchName.getValue == null
        || searchName.getValue.equals("")) {
        getWindow().showNotification(
          "Please enter a name for your search!",
          Notification.TYPE_WARNING_MESSAGE);
        return ;
      }
      app.saveSearch(searchFilter)
    }
    app.search(searchFilter)
  }
}