package it.xaan.cdn

import io.javalin.http.Context
import it.xaan.scalalin.UserError
import it.xaan.scalalin.rest.RouteCheck.Check

import scala.util.{Failure, Success}

object Checks {
  val Auth: Check[Any] = (ctx: Context, _: Any) =>
    if (ctx.headerMap().getOrDefault("Authorization", "") == Main.KEY)
      Success()
    else Failure(new UserError(401, Map("error" -> "unauthorised")))
}
