package models

import models._
import models.units._

class Player(
    val name: String, 
    var hp: Int, 
    var attack: Int, 
    val id: Int = 1, 
    val behaviour: Behaviour = new PlayerBehaviour()
  ) extends GameUnit {
  val fullHp = hp
  
  override def toString(): String = name
}

