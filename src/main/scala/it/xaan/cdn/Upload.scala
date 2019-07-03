package it.xaan.cdn

import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.util.concurrent.{Executors, TimeUnit}

import better.files.File
import io.javalin.http.Context
import it.xaan.cdn.Checks.Auth
import it.xaan.scalalin.rest.Route

class Upload extends Route[Any]("/upload", checks = Seq(Auth)) {
  private val scheduler = Executors.newSingleThreadScheduledExecutor()

  override def post(ctx: Context): Unit = {
    scheduler.schedule(new Runnable {
      override def run(): Unit = {
        val dir = File("./assets")
        val files = dir.walk().toList
        val count = files.size
        if (count >= 1000) {
          val list: List[File] = files.filter(!_.isDirectory).sortBy { file => Files.readAttributes(file.path, classOf[BasicFileAttributes]).creationTime().toInstant }
          val f = list(0)
          val att = Files.readAttributes(f.path, classOf[BasicFileAttributes]).creationTime().toInstant.toEpochMilli
          f.delete()
        }
      }
    }, 10, TimeUnit.MILLISECONDS)
    var id = Util.generateId()
    var file = File(s"./assets/$id.png")
    while (file.exists) {
      id = Util.generateId()
      file = File(s"./assets/$id.png")
    }
    Util.saveFile(ctx.uploadedFile("file").getContent, s"./assets/$id.png")
    ctx.result(s"https://cdn.xaan.it/$id.png").status(200)
  }
}
