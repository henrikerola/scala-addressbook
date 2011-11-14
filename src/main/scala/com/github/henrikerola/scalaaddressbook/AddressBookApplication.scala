package com.github.henrikerola.scalaaddressbook

import com.vaadin.demo.tutorial.addressbook.data.Person
import com.vaadin.demo.tutorial.addressbook.data.SearchFilter
import com.vaadin.ui.Window.Notification
import com.vaadin.ui.Component
import com.vaadin.ui.Table.ColumnGenerator
import com.vaadin.data.Property.ValueChangeListener
import com.vaadin.demo.tutorial.addressbook.data.PersonContainer
import com.vaadin.ui.Layout
import com.vaadin.ui.FormFieldFactory
import com.vaadin.ui.DefaultFieldFactory
import com.vaadin.data.Item
import com.vaadin.Application
import vaadin.scala._
import com.vaadin.data.util.BeanItem
import com.vaadin.terminal.Sizeable
import com.vaadin.terminal.ThemeResource
import com.vaadin.ui.Alignment
import com.vaadin.event.ItemClickEvent

class AddressBookApplication extends Application {
  
  private lazy val dataSource = PersonContainer.createWithTestData();

  private val tree = new NavigationTree(event => treeItemSelected(event));
  private val horizontalSplit = new HorizontalSplitPanel()

  private lazy val listView = new ListView(personList, personForm);
  private lazy val searchView = new SearchView(this);
  private lazy val personList = new PersonList(dataSource, _ => personSelected());
  private lazy val personForm = new PersonForm(dataSource);
  private lazy val helpWindow = new HelpWindow();
  private lazy val sharingOptions = new SharingOptions();

  def init(): Unit = {
    setMainWindow(new Window("Address Book Demo application"))

    val layout = new VerticalLayout(100 pct, 100 pct)
    layout.add(createToolbar());
    layout.add(horizontalSplit, ratio = 1);

    horizontalSplit.setSplitPosition(200, Sizeable.UNITS_PIXELS);
    horizontalSplit.setFirstComponent(tree);

    getMainWindow().setContent(layout);
    
    setTheme("contacts")
  }

  private def createToolbar(): HorizontalLayout = {
    new HorizontalLayout(width = 100 pct, margin = true, spacing = true, style = "toolbar") {
      val newContact = add(new Button("Add contact", _ => addNewContact()))
      newContact.setIcon(new ThemeResource("icons/32/document-add.png"))
      val search = add(new Button("Search", _ => showSearchView()))
      search.setIcon(new ThemeResource("icons/32/folder-add.png"))
      val share = add(new Button("Share", _ => showShareWindow()))
      share.setIcon(new ThemeResource("icons/32/users.png"))
      val help = add(new Button("Help", _ => showHelpWindow()))
      help.setIcon(new ThemeResource("icons/32/help.png"))
      add(new Embedded(source = new ThemeResource("images/logo.png")), ratio = 1, alignment = Alignment.MIDDLE_RIGHT)
    }
  }
  
  private def treeItemSelected(event: ItemClickEvent): Unit = event.getItemId match {
      case NavigationTree.showAll => {
        dataSource.removeAllContainerFilters();
        showListView();
      }
      case NavigationTree.search => showSearchView();
      case searchFilter: SearchFilter => search(searchFilter);
  }
  
  private def personSelected(): Unit = {
    val item = personList.getItem(personList.getValue());
    if (item != personForm.getItemDataSource()) {
    	personForm.setItemDataSource(item);
	}
  }

  private def setMainComponent(c: Component) = {
    horizontalSplit.setSecondComponent(c);
  }

  private def showHelpWindow() = {
    getMainWindow.addWindow(helpWindow);
  }

  private def showShareWindow() = {
    getMainWindow.addWindow(sharingOptions);
  }

  private def showListView() = {
    setMainComponent(listView);
  }

  private def showSearchView() = {
    setMainComponent(searchView);
  }

  private def addNewContact() = {
    showListView();
    personForm.addContact();
  }

  def search(searchFilter: SearchFilter) {
    dataSource.removeAllContainerFilters();
    dataSource.addContainerFilter(searchFilter.getPropertyId(),
      searchFilter.getTerm(), true, false);
    showListView();

    getMainWindow.showNotification(
      "Searched for " + searchFilter.getPropertyId() + "=*"
        + searchFilter.getTerm() + "*, found "
        + dataSource.size + " item(s).",
      Notification.TYPE_TRAY_NOTIFICATION);
  }

  def saveSearch(searchFilter: SearchFilter) = {
    tree.addItem(searchFilter)
    tree.setParent(searchFilter, NavigationTree.search)
    tree.setChildrenAllowed(searchFilter, false)
    tree.expandItem(NavigationTree.search)
    tree.setValue(searchFilter);
  }

}