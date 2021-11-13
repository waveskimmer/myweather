package org.scalatra.extensions.spray

import org.scalatra.extensions.validation.{ErrorCodes, Validated, ValidationError, ValidationResult}
import spray.json._

import javax.servlet.http.HttpServletRequest
import scala.util.{Failure, Success, Try}

trait SprayTool extends ErrorCodes {

  case class SprayReader[T](json: String)(implicit val reader: JsonReader[T]) {
    lazy val jsonAST: Try[JsValue] = Try {
      json.parseJson
    }
    lazy val binding: Try[T] = jsonAST match {
      case Success(value) => Try {
        value.convertTo
      }
      case Failure(exception) => Failure(exception)
    }
    lazy val value: T = binding match {
      case Success(value) => value
      case Failure(ex) => throw ex
    }

    def read: T = binding.get

  }

  def checkJson[T](reader: SprayReader[T])(request: HttpServletRequest): ValidationResult = {

    def checkBinding(binding: Try[T]): ValidationResult = binding match {
      case Success(v) => Validated
      case Failure(e) => ValidationError(JSON_BINDING, s"Internal Json Binding Error: ${e.getMessage}")
    }

    reader.jsonAST match {
      case Success(_) => checkBinding(reader.binding)
      case Failure(e) => ValidationError(JSON_MALFORMED, s"Json Malformed: ${e.getMessage}")
    }

  }


}
