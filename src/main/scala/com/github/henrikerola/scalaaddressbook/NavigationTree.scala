package com.github.henrikerola.scalaaddressbook

import vaadin.scala._

object NavigationTree {
  val showAll = "Show All"
  val search = "Search"
}

class NavigationTree(itemClickAction: com.vaadin.event.ItemClickEvent => Unit) 
    extends Tree(selectable = true, nullSelectionAllowed = false) {
  addItemClickListener(itemClickAction)
  
  addItem(NavigationTree.showAll);
  setChildrenAllowed(NavigationTree.showAll, false);
  addItem(NavigationTree.search);
}