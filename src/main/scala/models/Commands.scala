package models

import units.GameUnit

abstract class Command {
  val priority: Int;
}
case class AttackCommand(actor: GameUnit, target: GameUnit) extends Command {
  val priority: Int = 2
}
case class DefenceCommand(actor: GameUnit) extends Command {
  val priority: Int = 1
}
case class SkipTurnCommand(actor: GameUnit) extends Command {
  val priority: Int = 1
}