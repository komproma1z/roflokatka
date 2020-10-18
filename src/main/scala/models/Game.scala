package models

import scala.io.StdIn.readLine
import scala.util.Random
import scala.collection.mutable.ListBuffer

import units._
import models._
import services.CommandExecutor

object Game {


  def startGame(playerName: String): Unit = {
    println("Whenever you feel like quitting, just type in \"exit\".")
    val player = GameUnitFaсtory.createPlayer(playerName)
    enterDungeon(player)
  }

  def endGame(): Unit = {
    println(s"Game ended. Thanks for playing!")
    System.exit(0)
  }

  def enterDungeon(player: Player): Unit = {
    val monsters = ListBuffer[Monster](
      GameUnitFaсtory.createMonster("regular"),
      GameUnitFaсtory.createMonster("randBers"),
      GameUnitFaсtory.createMonster("seqBers")
    )

    for (monster <- monsters) {
      startFight(player, monster)
      player.hp = player.fullHp
    }
  }

  def startFight(player: GameUnit, monster: GameUnit): Unit = {
    val fightingUnits = ListBuffer(player, monster)
    if (GameData.get_currentTurn() != 1) GameData.reset_currentTurn()
    println(s"---------${player.toString()} starts fighting with ${monster.toString()}---------")

    def makeTurn(): Unit = {
      val monsterAction: Action = monster.getNextAction()
      val playerAction = player.getNextAction()
      monsterAction match {
        case AttackAction() => {
          CommandExecutor.addCommand(new AttackCommand(monster, player))
        }
        case DefenceAction() => {
          CommandExecutor.addCommand(new DefenceCommand(monster))
        }
        case SkipTurnAction() => {
          CommandExecutor.addCommand(new SkipTurnCommand(monster))
        }
      }

      playerAction match {
        case GameEndAction() => {
          Game.endGame()
        }
        case AttackAction() => {
          CommandExecutor.addCommand(new AttackCommand(player, monster))
        }
        case DefenceAction() => {
          CommandExecutor.addCommand(new DefenceCommand(player))
        }
      }
      CommandExecutor.run()
      Log.updateFightLog(monster.id)
      Log.fightLog(s"monster_${monster.id}-turn_${GameData.get_currentTurn()}").foreach(println)
      fightingUnits.foreach(unit => unit.resetStance())
      GameData.change_currentTurn()
    }
    while(player.isAlive() && monster.isAlive()) {
      makeTurn()
    }
    fightingUnits.clear()
  }
}

object GameData {
  private var currentTurn = 1

  def get_currentTurn(): Int ={ 
    currentTurn 
  }

  def change_currentTurn(): Unit ={ 
    currentTurn += 1
  }

  def reset_currentTurn(): Unit ={ 
    currentTurn = 1
  } 
}

