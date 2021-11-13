package org.scalatra.extensions.spray

import org.scalatest.funsuite.AnyFunSuite
import org.scalatra.extensions.validation.{Validated, ValidationError}
import spray.json._

case class Person(firstName: String, lastName: String)

trait TestBindings extends DefaultJsonProtocol {
  implicit val personFormat: RootJsonFormat[Person] = jsonFormat2(Person)
}

class SprayToolSpec extends AnyFunSuite with SprayTool with TestBindings {

  test("first spray") {

    val source =
      """{
        | "firstName": "Albert",
        | "lastName": "Einstein"
        | }""".stripMargin

    val jsonAst = source.parseJson // or JsonParser(source)

    println(jsonAst.prettyPrint)
    val person = jsonAst.convertTo[Person]

    val expected = Person("Albert", "Einstein")
    assert(person == expected)
  }

  test("happy SprayTool") {

    val source =
      """{
        | "firstName": "Albert",
        | "lastName": "Einstein"
        | }""".stripMargin
    val reader = SprayReader[Person](source)
    checkJson(reader)(null) match {
      case Validated =>
      case err => fail(s"Unexpected: $err")
    }
    val person = reader.read
    assert(person == Person("Albert", "Einstein"))

  }

  test("malformed SprayTool") {

    val source =
      """{ [
        | "firstName": "Albert",
        | "lastName": "Einstein"
        | }""".stripMargin
    val reader = SprayReader[Person](source)
    checkJson(reader)(null) match {
      case err: ValidationError => assert(err.code == JSON_MALFORMED)
      case other => fail(other.toString)
    }

  }

  test("missing json") {

    val reader = SprayReader[Person](null)
    checkJson(reader)(null) match {
      case err: ValidationError => assert(err.code == JSON_MALFORMED)
      case other => fail(other.toString)
    }

  }

  test("binding error") {
    val source =
      """{
        | "fitName": "Albert",
        | "lastName": "Einstein"
        | }""".stripMargin
    //    val person = source.parseJson.convertTo[Person]
    val reader = SprayReader[Person](source)
    checkJson(reader)(null) match {
      case err: ValidationError => assert(err.code == JSON_BINDING)
      case other => fail(other.toString)
    }

  }



  //  test("malformed json") {
  //
  //    val source =
  //      """{ [
  //        | "fitName": "Albert",
  //        | "lastName": "Einstein"
  //        | }""".stripMargin
  //    val mySpray = SprayIt(source)
  //
  //    SprayIt(source)
  //    val jsonAst = source.parseJson // or JsonParser(source)
  //
  //    //    println(jsonAst.prettyPrint)
  //    //    val person = jsonAst.convertTo[Person]
  //    //
  //    //    val expected = Person("Albert", "Einstein")
  //    //    assert(person == expected)
  //
  //
  //  }


}
