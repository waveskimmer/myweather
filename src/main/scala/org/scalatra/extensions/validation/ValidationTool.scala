package org.scalatra.extensions.validation

import scala.collection.View.Empty
import scala.util.{Failure, Success, Try}

trait ValidationResult

case object Validated extends ValidationResult

case class ValidationError(val code: Long, val message: String) extends ValidationResult

case class ValidationErrors(val errors: Seq[ValidationError]) extends ValidationResult

case class ValidationFailure(val errors: Seq[ValidationError]) extends Exception()


trait ValidationTool {
  type Validator[T] = T => ValidationResult

  def validate[T](target: T, validators: Validator[T]*): Try[T] =
    validators.flatMap(_.apply(target) match {
      case Validated => Empty
      case r: ValidationErrors => r.errors
      case error: ValidationError => Seq(error)
    }) match {
      case r if r.isEmpty => Success(target)
      case errors => Failure(new ValidationFailure(errors))
    }


}
