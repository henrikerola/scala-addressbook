package com.github.henrikerola.scalaaddressbook

import vaadin.scala.{ TextField => _, _ }
import com.vaadin.demo.tutorial.addressbook.data.PersonContainer
import scala.collection.JavaConversions._
import com.vaadin.ui.DefaultFieldFactory
import com.vaadin.demo.tutorial.addressbook.data.Person
import com.vaadin.data.util.BeanItem
import com.vaadin.data.Item
import com.vaadin.ui.Component
import com.vaadin.ui.Field
import com.vaadin.data.validator.RegexpValidator
import com.vaadin.data.validator.EmailValidator
import com.vaadin.ui.TextField

class PersonForm(dataSource: PersonContainer) extends Form {

  private var newContactMode = false
  private var newPerson: Person = _

  private val cities = new ComboBox("City") {
    setNewItemsAllowed(true);
    setNullSelectionAllowed(false);

    addItem("");
    for (person <- dataSource.getItemIds) {
      addItem(person.getCity())
    }
  }

  setWriteThrough(false)

  private val footer = new HorizontalLayout(spacing = true)
  footer.setVisible(false)
  setFooter(footer)

  private val saveButton = footer.add(new Button("Save", _ => save))
  private val cancelButton = footer.add(new Button("Cancel", _ => cancel))
  private val editButton = footer.add(new Button("Edit", _ => setReadOnly(false)))

  setFormFieldFactory(new DefaultFieldFactory() {
    override def createField(item: Item, propertyId: AnyRef, uiContext: Component): Field =
      propertyId match {
        case "city" => cities
        case "postalCode" =>
          val tf = super.createField(item, propertyId, uiContext).asInstanceOf[TextField]
          tf.setNullRepresentation("");
          tf.addValidator(new RegexpValidator("[1-9][0-9]{4}", "Postal code must be a five digit number and cannot start with a zero."));
          tf.setRequired(true);
          tf.addValidator(new RegexpValidator("[1-9][0-9]{4}", "Postal code must be a five digit number and cannot start with a zero."));
          tf.setRequired(true);
          tf
        case "email" =>
          val field = super.createField(item, propertyId, uiContext)
          field.addValidator(new EmailValidator(
            "Email must contain '@' and have full domain."));
          field.setRequired(true);
          field
        case _ => super.createField(item, propertyId, uiContext)
      }
  })

  private def save(): Unit = {
    if (isValid()) {
      commit()
      if (newContactMode) {
        setItemDataSource(dataSource.addItem(newPerson))
        newContactMode = false
      }
      setReadOnly(true)
    }
  }

  private def cancel(): Unit = {
    if (newContactMode) {
      newContactMode = false
      setItemDataSource(null)
    } else {
      discard()
    }
    setReadOnly(true)
  }

  override def setItemDataSource(newDataSource: Item) = {
    newContactMode = false;

    if (newDataSource != null) {
      val orderedProperties = PersonContainer.NATURAL_COL_ORDER.toList
      super.setItemDataSource(newDataSource, orderedProperties);
      setReadOnly(true);
      getFooter().setVisible(true);
    } else {
      super.setItemDataSource(null);
      getFooter().setVisible(false);
    }
  }

  override def setReadOnly(readOnly: Boolean) = {
    super.setReadOnly(readOnly);
    saveButton.setVisible(!readOnly);
    cancelButton.setVisible(!readOnly);
    editButton.setVisible(readOnly);
  }

  def addContact() = {
    newPerson = new Person();
    setItemDataSource(new BeanItem[Person](newPerson));
    newContactMode = true;
    setReadOnly(false);
  }
}