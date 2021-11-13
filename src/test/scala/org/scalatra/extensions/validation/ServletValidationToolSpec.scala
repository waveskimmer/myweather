package org.scalatra.extensions.validation

import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar
import org.scalatra.Action

import javax.servlet.http.HttpServletRequest
import scala.language.implicitConversions
import scala.util.Failure

//noinspection NotImplementedCode
class ServletValidationToolSpec extends AnyFunSuite with ServletValidationTool with MockitoSugar {


  test("Authorization is missing") {
    val request = mock[HttpServletRequest]
    when(request.getHeader(any)).thenReturn(null)

    validate[HttpServletRequest](request, checkAuthorization) match {
      case Failure(ValidationFailure(Seq(ValidationError(HEADER_PARAMETER_MISSING, _)))) =>
      case wrong => fail(s"Unexpected: $wrong")
    }
  }

  test("Authorization is malformed") {
    val request = mock[HttpServletRequest]
    when(request.getHeader(any)).thenReturn("howdy duty")

    validate[HttpServletRequest](request, checkAuthorization) match {
      case Failure(ValidationFailure(Seq(ValidationError(HEADER_PARAMETER_MALFORMED, _)))) =>
      case wrong => fail(s"Unexpected: $wrong")
    }
  }

  // these flush out the ServletValidationTool
  override protected var doNotFound: Action = _

  override protected def routeBasePath(implicit request: HttpServletRequest): String = ???

  override def requestPath(implicit request: HttpServletRequest): String = ???

  override type ConfigT = this.type

  override protected implicit def configWrapper(config: ServletValidationToolSpec.this.type): Config = ???
}
