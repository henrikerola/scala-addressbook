package com.vaadin.demo.tutorial.addressbook.data

import java.io.Serializable
import java.util.Random

import com.vaadin.data.util.BeanItemContainer

class PersonContainer extends BeanItemContainer[Person](classOf[Person]) with Serializable

object PersonContainer  {
    /**
     * Natural property order for Person bean. Used in tables and forms.
     */
  val NATURAL_COL_ORDER:Array[Object] = Array(
            "firstName", "lastName", "email", "phoneNumber", "streetAddress",
            "postalCode", "city")
    /**
     * "Human readable" captions for properties in same order as in
     * NATURAL_COL_ORDER.
     */
  val COL_HEADERS_ENGLISH = Array(
            "First name", "Last name", "Email", "Phone number",
            "Street Address", "Postal Code", "City")

  def createWithTestData(): PersonContainer = {
        val fnames = Array( "Peter", "Alice", "Joshua", "Mike", "Olivia",
                "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
                "Lisa", "Marge" )
        val lnames = Array( "Smith", "Gordon", "Simpson", "Brown",
                "Clavel", "Simons", "Verne", "Scott", "Allison", "Gates",
                "Rowling", "Barks", "Ross", "Schneider", "Tate" )
        val cities = Array( "Amsterdam", "Berlin", "Helsinki",
                "Hong Kong", "London", "Luxemburg", "New York", "Oslo",
                "Paris", "Rome", "Stockholm", "Tokyo", "Turku" )
        val streets = Array( "4215 Blandit Av.", "452-8121 Sem Ave",
                "279-4475 Tellus Road", "4062 Libero. Av.", "7081 Pede. Ave",
                "6800 Aliquet St.", "P.O. Box 298, 9401 Mauris St.",
                "161-7279 Augue Ave", "P.O. Box 496, 1390 Sagittis. Rd.",
                "448-8295 Mi Avenue", "6419 Non Av.",
                "659-2538 Elementum Street", "2205 Quis St.",
                "252-5213 Tincidunt St.", "P.O. Box 175, 4049 Adipiscing Rd.",
                "3217 Nam Ave", "P.O. Box 859, 7661 Auctor St.",
                "2873 Nonummy Av.", "7342 Mi, Avenue",
                "539-3914 Dignissim. Rd.", "539-3675 Magna Avenue",
                "Ap #357-5640 Pharetra Avenue", "416-2983 Posuere Rd.",
                "141-1287 Adipiscing Avenue", "Ap #781-3145 Gravida St.",
                "6897 Suscipit Rd.", "8336 Purus Avenue", "2603 Bibendum. Av.",
                "2870 Vestibulum St.", "Ap #722 Aenean Avenue",
                "446-968 Augue Ave", "1141 Ultricies Street",
                "Ap #992-5769 Nunc Street", "6690 Porttitor Avenue",
                "Ap #105-1700 Risus Street",
                "P.O. Box 532, 3225 Lacus. Avenue", "736 Metus Street",
                "414-1417 Fringilla Street", "Ap #183-928 Scelerisque Road",
                "561-9262 Iaculis Avenue" )
        val r = new Random(0)
        val c = new PersonContainer()
        for (i <- 0 until 100) {
          val firstName = fnames(r.nextInt(fnames.length))
          val lastName = lnames(r.nextInt(lnames.length))
          val postalCode = {
            val n = r.nextInt(100000)
    		if (n < 10000) n + 10000 else n
          }

          c.addItem(new Person(
        	firstName,
        	lastName,
            email = firstName.toLowerCase() + "."
                    + lastName.toLowerCase() + "@vaadin.com",
            phoneNumber = "+358 02 555 " + r.nextInt(10) + r.nextInt(10)
                    + r.nextInt(10) + r.nextInt(10),
            streets(r.nextInt(streets.length)),
            postalCode,
            city = cities(r.nextInt(cities.length))
          ))
        }
        c
    }
}
