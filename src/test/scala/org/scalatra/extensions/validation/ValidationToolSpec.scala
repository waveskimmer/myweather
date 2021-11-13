package org.scalatra.extensions.validation

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

import scala.language.implicitConversions
import scala.util.{Failure, Success}

//noinspection NotImplementedCode,ScalaUnusedSymbol
class ValidationToolSpec extends AnyFunSuite with ValidationTool with MockitoSugar {

  test("happy case works") {
    case class Simple(name: String)

    val simple = Simple("Einstein")

    def checkName(simple: Simple): ValidationResult = simple match {
      case s if s.name.startsWith("Ein") => Validated
      case s => ValidationError(101, s"$s not right")
    }

    validate[Simple](simple, checkName) match {
      case Success(simple) =>
      case wrong => fail(s"Unexpected: $wrong")
    }

  }

  test("unhappy case detected") {
    case class Simple(name: String)

    val simple = Simple("Einstein")

    def checkName(simple: Simple): ValidationResult = simple match {
      case s if s.name == "Oppenheimer" => Validated
      case s => ValidationError(101, s"$s not right")
    }

    validate[Simple](simple, checkName) match {
      case Failure(ValidationFailure(Seq(ValidationError(101, _)))) =>
      case wrong => fail(s"Unexpected: $wrong")
    }
  }

}

