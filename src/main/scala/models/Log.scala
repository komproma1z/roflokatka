package models

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.Map

object Log {
  val messages = ListBuffer[String]()
  val fightLog = Map[String, ListBuffer[String]]()

  def logMessage(message: String): Unit = {
    messages.append(message)
  }
  
  def updateFightLog(currentMonsterId: Int): Unit = {
    fightLog.addOne(s"monster_$currentMonsterId-turn_${GameData.get_currentTurn()}" -> messages.map(msg => msg))
    messages.clear()
  }
}