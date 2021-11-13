package org.scalatra.extensions.validation

/**
 * This should be extended by the Application - we'll reserve 1000-1999 here.
 */
trait ErrorCodes {
  private val start = 1000
  val HEADER_PARAMETER_MALFORMED: Int = start + 1
  val HEADER_PARAMETER_MISSING: Int = HEADER_PARAMETER_MALFORMED + 1
  val JSON_MALFORMED: Int = HEADER_PARAMETER_MISSING + 1
  val JSON_BINDING: Int = JSON_MALFORMED + 1
}

object ErrorCodes extends ErrorCodes

