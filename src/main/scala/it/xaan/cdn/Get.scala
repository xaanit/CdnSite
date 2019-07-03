package it.xaan.cdn

import better.files.File
import io.javalin.http.Context
import it.xaan.scalalin.rest.Route

class Get extends Route[Any]("/:image") {
  override def get(ctx: Context): Unit = {
    val file = File(s"./assets/${ctx.pathParam("image")}")
    if (!file.exists)
      ctx.status(404)
    else {
      val is = file.newFileInputStream
      ctx.result(is).header("Content-Type", "image/png").status(200)
    }
  }
}
