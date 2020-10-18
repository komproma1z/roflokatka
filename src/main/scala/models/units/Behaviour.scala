package models.units

import scala.io.StdIn.readLine
import scala.util.Random

import models._

// Sames stuff as wtih RegularBehaviour
trait Behaviour {
  def getNextAction(): Action
}

class PlayerBehaviour extends Behaviour {
  implicit def getActionFromString(value: String) = {
    value match {
      case "exit" => GameEndAction()
      case "a"   => AttackAction()
      case "b"   => DefenceAction()
      case other => InvalidAction()
    }
  }

  override def getNextAction(): Action = {
    var playerAction: Action = InvalidAction()
    while (playerAction == InvalidAction()) {
      println("Type \"a\" to attack or \"b\" for block")
      playerAction = readLine() 
    }
    playerAction
  }
}
trait MonsterBehaviour extends Behaviour

// If I name it "RegularBehaviour" it complains lol
class RegularBehaviour extends MonsterBehaviour {
  override def getNextAction(): Action = {
    val actions = ActionCreator.getUsefulActions()
    val index = Random.nextInt(actions.length)
    actions(index)
  }
}

class BerserkRandomBehaviour extends MonsterBehaviour {
  override def getNextAction(): Action = {
    val tenPercentChance = Random.nextInt(10)
    if (tenPercentChance == 7) {
      SkipTurnAction()
    } else {
      val actions = ActionCreator.getUsefulActions()
      val index = Random.nextInt(actions.length)
      actions(index)
    }
  }
}

class BerserkSeqBehaviour extends MonsterBehaviour {
  override def getNextAction(): Action = {
    if (GameData.get_currentTurn() % 4 == 0) {
      SkipTurnAction()
    } else {
      val actions = ActionCreator.getUsefulActions()
      val index = Random.nextInt(actions.length)
      actions(index)
    }
  }
}
