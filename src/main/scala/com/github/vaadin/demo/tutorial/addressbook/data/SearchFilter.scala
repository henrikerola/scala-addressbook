package com.vaadin.demo.tutorial.addressbook.data

import java.io.Serializable

class SearchFilter (
    val propertyId: Object,
    val term: String,
    val name: String
) extends Serializable 