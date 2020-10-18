package models.units

import scala.util.Random

import models._
import models.units.GameUnit


class Monster(
    val id: Int, 
    var hp: Int, 
    var attack: Int, 
    val behaviour: MonsterBehaviour, 
    val name: String = "Monster"
  ) extends GameUnit with MonsterBehaviour {
  override def toString(): String = s"$name($id)"
}