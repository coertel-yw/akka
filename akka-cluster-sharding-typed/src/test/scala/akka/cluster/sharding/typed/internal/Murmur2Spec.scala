/*
 * Copyright (C) 2021 Lightbend Inc. <https://www.lightbend.com>
 */

package akka.cluster.sharding.typed.internal

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.nio.charset.StandardCharsets

class Murmur2Spec extends AnyWordSpec with Matchers {
  "The Murmur2 implementation" must {
    "calculate the correct checksum for inputs of any length" in {
      // Expected values computed using Kafka's murmur2-implementation
      Seq(
        "1" -> -1993445489,
        "12" -> 126087238,
        "123" -> -267702483,
        "1234" -> -1614185708,
        "12345" -> -1188365604,
        "12345678" -> 338742798,
        "123456789012" -> -1769968653,
        "ABCDEFGH" -> -1542716145
      ).foreach {
        case (input, expectedHash) =>
          Murmur2.murmur2(input.getBytes(StandardCharsets.UTF_8)) should ===(expectedHash)
      }
    }
  }
}
