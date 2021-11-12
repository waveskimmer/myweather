package org.myweather.app

import org.scalatra.test.scalatest._

class MyWeatherServletTests extends ScalatraFunSuite {

  addServlet(classOf[MyWeatherServlet], "/*")

  test("GET / on MyWeatherServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

  override def header = ???
}
