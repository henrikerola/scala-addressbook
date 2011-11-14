name := "scaladocumentmanager"
 
scalaVersion := "2.9.1"
 
seq(webSettings: _*)

libraryDependencies ++= Seq(
  "com.vaadin" % "vaadin" % "6.7.1",
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container"
)