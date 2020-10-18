package models.units

import scala.util.Random

import models._

trait GameUnit {
  val id: Int
  val name: String
  var hp: Int
  var attack: Int
  var isInDefence: Boolean = false
  val behaviour: Behaviour
  def isAlive(): Boolean = {
    hp > 0
  }
  def getDamaged(damage: Int): Unit = {
    if (!isInDefence) {
      hp -= damage
    }
  }
  def takeDefence(): Unit = isInDefence = true
  def resetStance(): Unit = isInDefence = false
  def getNextAction(): Action = behaviour.getNextAction()
  // def calculateDamage(target: GameUnit): Int TODO: Если нужно будет вычислять урон по формуле (криты, мб армор и тд)
}

object GameUnitFaсtory {
  def createPlayer(name: String): Player = {
    new Player(name, Random.between(20, 31), Random.between(3, 6))
  }
  def createMonster(monsterType: String): Monster = {
    monsterType match {
      case "regular" => new Monster(1, 10, 2, new RegularBehaviour())
      case "randBers" => new Monster(2, 20, 4, new BerserkRandomBehaviour())
      case "seqBers" => new Monster(3, 30, 9, new BerserkSeqBehaviour())
      // handle other
    }
  }
}