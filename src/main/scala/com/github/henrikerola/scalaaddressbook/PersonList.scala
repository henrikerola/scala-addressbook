package com.github.henrikerola.scalaaddressbook

import vaadin.scala._
import com.vaadin.demo.tutorial.addressbook.data.PersonContainer
import com.vaadin.demo.tutorial.addressbook.data.Person
import com.vaadin.terminal.ExternalResource

class PersonList(dataSource: PersonContainer, action: com.vaadin.data.Property.ValueChangeEvent => Unit)
  extends Table(width = 100 pct, height = 100 pct, dataSource = dataSource) {
  addListener(action)

  setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
  setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);

  setColumnCollapsingAllowed(true);
  setColumnReorderingAllowed(true);

  setSelectable(true);
  setImmediate(true);
  setNullSelectionAllowed(false);

  addGeneratedColumn("email", (table, itemID, propertyId) => itemID match {
    case p: Person => new Link(p.getEmail(), new ExternalResource("mailto:" + p.getEmail()))
  });
}