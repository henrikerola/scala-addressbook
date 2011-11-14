package com.github.henrikerola.scalaaddressbook

import vaadin.scala._

class HelpWindow extends Window(caption = "Address Book help") {
  add(new HtmlLabel("This is "
    + "an application built during <strong><a href=\""
    + "http://dev.vaadin.com/\">Vaadin</a></strong> "
    + "tutorial. Hopefully it doesn't need any real help."))
}
