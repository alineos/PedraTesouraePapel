package br.edu.ifsp.dmo.pedratesouraepapel.model

class War(buttles: Int, player1: String, player2: String) {
    private var buttle: Int = 0
    private var foughtButtle: Int = 0
    val opponent1: Player = Player(player1)
    val opponent2: Player = Player(player2)
    init {
        buttle = (if (buttles > 0) buttles else 1)
    }
    fun getWinner(): Player {
        if (has_buttles()) {
            throw NoWarFinishException("The war did not finish.")
        } else {
            return if (opponent1.points >= opponent2.points) {
                opponent1
            } else {
                opponent2
            }
        }
    }
    fun toBattle(player1Weapon: Weapon, player2Weapon: Weapon): Player?
    {
        var winner: Player? = null
        if (has_buttles()) {
            if (player1Weapon != player2Weapon) {
                winner = when {
                    player1Weapon is Rock && player2Weapon is Scissors
                    -> opponent1
                    player1Weapon is Scissors && player2Weapon is Paper
                    -> opponent1
                    player1Weapon is Paper && player2Weapon is Rock ->
                        opponent1
                    else -> opponent2
                }
                winner.recordPoint()
                foughtButtle += 1
            } else {
                winner = null
            }
        }
        return winner
    }
    fun has_buttles() = foughtButtle < buttle
}
class NoWarFinishException(msg: String) : Exception(msg)
