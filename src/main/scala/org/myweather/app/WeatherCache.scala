package org.myweather.app

import org.myweather.app.Domain._

import scala.collection.mutable

/**
 * WARNING: Not thread safe - just for demonstration purposes.
 * this should be swapped out for a redis cache or similar.  Also, you could have different services doing
 * updates as well as read-only services that support web clients.
 */
object WeatherCache {
  var map: mutable.Map[String, Report] = mutable.Map[String, Report]()

  def put(report: Report): Option[Report] = {
    // probably want to compare timestamps and only allow the latest
    map.put(report.postalCode, report)
  }

  def get(key: String): Option[Report] = map.get(key: String)


}