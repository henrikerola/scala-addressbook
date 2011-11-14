package com.github.henrikerola.scalaaddressbook

import vaadin.scala._

class SharingOptions
  extends Window(caption = "Sharing options", width = 50 pct, modal = true) {
  center();

  add(new Label("With these setting you can modify contact sharing "
    + "options. (non-functional, example of modal dialog)"));
  add(new CheckBox("Gmail"));
  add(new CheckBox(".Mac"));
  add(new Button("OK", _ => close()));
}