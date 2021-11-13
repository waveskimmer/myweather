package org.myweather.app

import org.scalatra._
import org.scalatra.extensions.spray.SprayTool
import org.scalatra.extensions.validation.ServletValidationTool

class MyWeatherServlet extends ScalatraServlet with SprayTool with ServletValidationTool with Domain {

  get("/:postalCode") {

    s"Success"
  }

  /**
   * convenience method to update weather.   mostly used for testing
   */
  post("/") {

    val reader = SprayReader[Report](request.body)
    validate(request, checkJson[Report](reader))
    val report = reader.read
    println(report)

  }


}
