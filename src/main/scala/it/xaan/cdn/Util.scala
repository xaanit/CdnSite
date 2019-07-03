package it.xaan.cdn

import java.io.{FileOutputStream, InputStream}
import java.util.Random

import org.apache.commons.io.IOUtils


object Util {

  def saveFile(is: InputStream, file: String): Unit = {
    val os = new FileOutputStream(file)
    IOUtils.copy(is, os)
    is.close()
    os.close()
  }

  private val vowels = "aeiou".toCharArray
  private val constants = "qwrtypsdfghjklzxcvbnm".toCharArray

  def generateId(): String = {
    val random = new Random()
    val str = new StringBuilder()
    for (i <- 1 to 10) str.append(if (i % 2 == 0) constants(random.nextInt(constants.length)) else vowels(random.nextInt(vowels.length)))
    str.toString()
  }

}
