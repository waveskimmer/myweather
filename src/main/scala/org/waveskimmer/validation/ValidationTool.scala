package org.waveskimmer.validation

import scala.util.{Failure, Success, Try}

case class ValidationError(val code: Long, val message: String)

class ValidationErrors(val errors: Seq[ValidationError]) extends Exception

trait ValidationTool {
  type Validator[T] = T => Seq[ValidationError]

  def validate[T](target: T, validators: Validator[T]*): Try[T] =
    validators.flatMap(_.apply(target)) match {
      case s if s.isEmpty => Success(target)
      case errors => Failure(new ValidationErrors(errors))

    }

}
