package br.edu.ifsp.dmo.pedratesouraepapel.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pedratesouraepapel.R
import br.edu.ifsp.dmo.pedratesouraepapel.databinding.ActivitySelectionBinding
import br.edu.ifsp.dmo.pedratesouraepapel.model.Paper
import br.edu.ifsp.dmo.pedratesouraepapel.model.Rock
import br.edu.ifsp.dmo.pedratesouraepapel.model.Scissors
import br.edu.ifsp.dmo.pedratesouraepapel.model.Weapon


class SelectionActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivitySelectionBinding
    private var number: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Abrir o bundle e recuperar os dados
        var name: String? = ""
        val extras = intent.extras
        if (extras != null) {
            name = extras.getString(Constants.KEY_PLAYER_NAME)
            number = extras.getInt(Constants.KEY_PLAYER_NUMBER)
        }
        // Esconder a barra de ferramentas
        actionBar?.hide()
        // Apresentar mensagem
        binding.textviewMessage.text = "$name${getString(R.string.choose_gum)}"
        
        // Configurar os listener
        binding.buttonPaper.setOnClickListener(this)
        binding.buttonRock.setOnClickListener(this)
        binding.buttonScissors.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        val weapon = when {
            v == binding.buttonRock -> Rock
            v == binding.buttonPaper -> Paper
            v == binding.buttonScissors -> Scissors
            else -> null
        }
        handleResult(weapon)
    }
    private fun handleResult(weapon: Weapon?) {
        if (weapon == null) {
            setResult(RESULT_CANCELED)
        } else {
            val mIntent = Intent()
            mIntent.putExtra(Constants.KEY_PLAYER_NUMBER, number)
            mIntent.putExtra(Constants.KEY_WEAPON, weapon)
            setResult(RESULT_OK, mIntent)
        }
        finish()
    }
}