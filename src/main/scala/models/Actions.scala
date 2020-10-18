package models

abstract class Action

case class GameEndAction() extends Action
case class AttackAction() extends Action
case class DefenceAction() extends Action
case class InvalidAction() extends Action
case class SkipTurnAction() extends Action

object ActionCreator {
  def getUsefulActions() = {
    Array(AttackAction(), DefenceAction())
  }
}