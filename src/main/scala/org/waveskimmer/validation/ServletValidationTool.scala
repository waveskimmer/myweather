package org.waveskimmer.validation

import org.scalatra.ScalatraBase

import javax.servlet.http.HttpServletRequest
import scala.util.matching.Regex



trait ServletValidationTool extends ScalatraBase with ValidationTool with HttpHeaders {

  val authorizationRegex: Regex = raw"Bearer\s.*".r
  val applicationJsonRegex: Regex = "application/json".r


    Option(request.)





}
