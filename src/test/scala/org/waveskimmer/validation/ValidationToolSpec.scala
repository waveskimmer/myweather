package org.waveskimmer.validation

import org.scalatest.funsuite.AnyFunSuite

class ValidationToolSpec extends AnyFunSuite {

  case class Request(val name: String,
                     val num: String,
                    )

  test ("going to town") {
    assert(true)
  }



}
