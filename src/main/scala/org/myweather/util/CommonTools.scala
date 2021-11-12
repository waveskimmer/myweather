package org.myweather.util

import java.util.UUID

trait CommonTools {
  def createUUID(): String = UUID.randomUUID().toString
}

object CommonTools extends CommonTools
