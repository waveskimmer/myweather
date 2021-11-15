package org.scalatra.extensions.validation

import org.scalatra.ScalatraBase

import javax.servlet.http.HttpServletRequest
import scala.util.matching.Regex

trait ValidationRegex {
  val authorizationRegex: Regex = raw"Bearer\s.*".r
  val applicationJsonRegex: Regex = "application/json".r
  val notEmptyRegex: Regex = raw".+".r
}


trait ServletValidationTool extends ScalatraBase with ValidationTool with HttpHeaders with ValidationRegex with ErrorCodes {

  def checkHeaderParam(param: String, regex: Regex = notEmptyRegex)(request: HttpServletRequest): ValidationResult =
    Option(request.getHeader(param)) match {
      case Some(regex(_*)) => Validated
      case Some(value) => ValidationError(HEADER_PARAMETER_MALFORMED, s"Header parameter '$param' malformed: $value")
      case None => ValidationError(HEADER_PARAMETER_MISSING, s"Header parameter '$param' missing")
    }

  //  def checkPathParam(param: String, regex: Regex = notEmptyRegex)

  def checkAuthorization(request: HttpServletRequest): ValidationResult =
    checkHeaderParam(authorization, authorizationRegex)(request)

}
