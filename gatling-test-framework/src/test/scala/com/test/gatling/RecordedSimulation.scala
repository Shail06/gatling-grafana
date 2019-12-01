package com.test.gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://computer-database.gatling.io")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.14; rv:67.0) Gecko/20100101 Firefox/67.0")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "image/webp,*/*")

	val headers_2 = Map(
		"DNT" -> "1",
		"Pragma" -> "no-cache",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_4 = Map(
		"Accept" -> "image/webp,*/*",
		"DNT" -> "1",
		"Pragma" -> "no-cache")



	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0


			)
			.resources(http("request_1")
			.get("/favicon.ico")
			.headers(headers_1)
			.check(status.is(404))))
		.pause(14)
		.exec(http("request_2")
			.get("/computers")
			.headers(headers_2)
			.resources(http("request_3")
			.get("/computers?f=macbook")
			.headers(headers_0),
            http("request_4")
			.get("/favicon.ico")
			.headers(headers_4)
			.check(status.is(404))))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}