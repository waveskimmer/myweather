package org.myweather.app

import org.scalatra._

class MyWeatherServlet extends ScalatraServlet {

  get("/weather") {

    s"Success"
  }

}
