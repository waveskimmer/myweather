package org.myweather.app

import org.myweather.app.Domain._
import org.scalatra._
import org.scalatra.extensions.spray.SprayTool
import org.scalatra.extensions.validation.ServletValidationTool

class MyWeatherServlet extends ScalatraServlet with SprayTool with ServletValidationTool {

  get("/:postalCode") {
    Option(params("postalCode")).flatMap(c => WeatherCache.get(c)).toList.toJson.prettyPrint
  }

  /**
   * convenience method to update weather.   mostly used for testing
   */
  post("/") {
    val reader = SprayReader[Report](request.body)
    validate(request, checkJson[Report](reader))
    val report = reader.read
    WeatherCache.put(report)
    report.toJson.prettyPrint
  }


}
