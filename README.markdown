A version of Vaadin AddressBook created with Scala & [scala-wrappers](http://vaadin.com/addon/scala-wrappers).

### How to run the application?

Install [sbt 0.11](https://github.com/harrah/xsbt/wiki) and run the following command:

    $ sbt ~container:start

After that the application should be running at [http://localhost:8080/](http://localhost:8080/). The command also listens changes make to code and re-deploys the application automatically.

### Generate project files for Eclipse

Project uses the [sbteclipse](https://github.com/typesafehub/sbteclipse) sbt plugin and eclipse project files can be generated by saying:

    $ sbt eclipse

After that the project can be imported into a Eclipse workspace. Install also [Scala IDE for Eclipse](http://www.scala-ide.org/).