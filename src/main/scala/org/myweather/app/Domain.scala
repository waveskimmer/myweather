package org.myweather.app

import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait Domain extends DefaultJsonProtocol {

  case class Wind(avg: Int,
                  max: Int,
                  direction: String)

  case class Report(postalCode: String,
                    system: String, // METRIC, IMPERIAL, etc.
                    temp: Int,
                    humidity: Int, // percent
                    wind: Wind
                   )

  implicit val windFormat: RootJsonFormat[Wind] = jsonFormat3(Wind)
  implicit val reportFormat: RootJsonFormat[Report] = jsonFormat5(Report)

}



