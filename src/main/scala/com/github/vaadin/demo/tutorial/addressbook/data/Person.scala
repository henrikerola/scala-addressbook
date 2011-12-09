package com.vaadin.demo.tutorial.addressbook.data

import java.io.Serializable
import scala.reflect.BeanProperty


class Person(
     @BeanProperty var firstName: String = "",
     @BeanProperty var lastName: String = "",
     @BeanProperty var email: String = "",
     @BeanProperty var phoneNumber: String = "",
     @BeanProperty var streetAddress: String = "",
     @BeanProperty var postalCode: Int = 0,
     @BeanProperty var city: String = ""
) extends Serializable 
