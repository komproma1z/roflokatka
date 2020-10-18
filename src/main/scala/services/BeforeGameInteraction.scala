package services

import scala.io.StdIn.readLine

import models.Game

object BeforeGameInteraction {
  def greet(): Unit = println("Hey, welcome to Roflo-RPG!")

  def askForReadiness(): Unit = {
    println("If you are reay to start, type in \"y\", if not, then \"n\".")
    var isReady = readLine()
    if (isReady == "y") {
      println("How are we going to name your character?")
      val name = readLine()
      Game.startGame(name)
    } else if (isReady == "n") {
      Game.endGame()
    } else {
      println("Please use either \"y\" or \"n\"")
      askForReadiness()
    }
  }
}
