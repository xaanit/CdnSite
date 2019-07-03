package it.xaan.cdn

import better.files.File
import io.javalin.Javalin
import it.xaan.scalalin.rest.Route


object Main {
  val KEY = File("./key.txt").lines.mkString("")

  def main(args: Array[String]): Unit = {
    val file = File("./assets")
    if (!file.exists)
      file.createDirectory()
    Javalin.create()
      .routes(Route.getEndpoints(Seq[Route[Any]](new Upload(), new Get())))
      .start(8080)
  }
}
