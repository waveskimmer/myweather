package org.scalatra.extensions.validation

/**
 * This should be extended by the Application - we'll reserve 1000-1999 here.
 */
trait ErrorCodes {
  private val start = 1000
  val AUTHORIZATION_HEADER_MALFORMED: Int = start + 1
  val AUTHORIZATION_HEADER_MISSING: Int = AUTHORIZATION_HEADER_MALFORMED + 1
}
