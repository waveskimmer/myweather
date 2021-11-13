package org.scalatra.extensions.validation

import org.scalatra.ScalatraBase

import javax.servlet.http.HttpServletRequest
import scala.util.matching.Regex

trait ValidationRegex {
  val authorizationRegex: Regex = raw"Bearer\s.*".r
  val applicationJsonRegex: Regex = "application/json".r
}


trait ServletValidationTool extends ScalatraBase with ValidationTool with HttpHeaders with ValidationRegex with ErrorCodes {

  def checkAuthorization(request: HttpServletRequest): ValidationResult =
    Option(request.getHeader(authorization)) match {
      case Some(authorizationRegex(_*)) => Validated
      case Some(value) => ValidationError(AUTHORIZATION_HEADER_MALFORMED, s"Header parameter '$authorization' malformed: $value")
      case None => ValidationError(AUTHORIZATION_HEADER_MISSING, s"Missing header parameter '$authorization'")
    }

}
