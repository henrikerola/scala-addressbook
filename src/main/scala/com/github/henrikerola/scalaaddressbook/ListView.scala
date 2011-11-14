package com.github.henrikerola.scalaaddressbook

import vaadin.scala.VerticalSplitPanel

class ListView(personList: PersonList, personForm: PersonForm)
    extends VerticalSplitPanel(style = "view") {
  setFirstComponent(personList);
  setSecondComponent(personForm);
  setSplitPosition(40);
}