package org.myweather.util

import java.time.format.DateTimeFormatter
import java.time.{ZoneOffset, ZonedDateTime}
import java.util.UUID

trait CommonTools {
  def createUUID(): String = UUID.randomUUID().toString

  def timestamp: String = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)

}

object CommonTools extends CommonTools

