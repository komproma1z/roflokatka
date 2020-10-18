package services

import scala.collection.mutable.ListBuffer

import models._

object CommandExecutor {
  val commands: ListBuffer[Command] = new ListBuffer()
  def addCommand(command: Command): Unit = {
    commands.addOne(command)
  }
  def run(): Unit = {
    commands.sortBy(_.priority).foreach(execute)
    commands.clear()
  }
  def execute(command: Command): Unit = {
    command match {
      case AttackCommand(actor, target) => {
        target.getDamaged(actor.attack)
        target.isInDefence match {
          case true => {
            Log.logMessage(s"${actor.toString()} failed to damage ${target.toString()}. ${target.toString()} is in deep defence")
          }
          case false => {
            if (target.hp > 0) {
              Log.logMessage(s"${actor.toString()} damaged ${target.toString()} for ${actor.attack}. ${target.toString()} has ${target.hp} hp left")
            } else {
              Log.logMessage(s"${actor.toString()} defeats ${target.toString()}")
            }
          }
        }
      }
      case DefenceCommand(actor) => {
        actor.takeDefence()
        Log.logMessage(s"${actor.toString()} took defence stance. Their current hp is ${actor.hp}")
      }
      case SkipTurnCommand(actor) => {
        Log.logMessage(s"${actor.toString()} skipped turn")
      }
    }
  }
}