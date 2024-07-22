package br.edu.ifsp.dmo.pedratesouraepapel.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pedratesouraepapel.R
import br.edu.ifsp.dmo.pedratesouraepapel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configToolBar()
        configSpinner()
        configListener()
        atualizaGUI()


    }
    private fun atualizaGUI(){
        binding.spinnerPlayer.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position ==0 ){
                    binding.edittextPlayer2.text.clear()
                    binding.edittextPlayer2.visibility = View.INVISIBLE

                }else
                    binding.edittextPlayer2.visibility = View.VISIBLE
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }
    private fun configListener() {
        binding.buttonStart.setOnClickListener { startGame() }

    }
    private fun configSpinner() {
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,resources.getStringArray(R.array.tipos_jogos)
        )
        binding.spinnerButtles.adapter = adapter

        val adapterPayer = ArrayAdapter<String>(
            this, android.R.layout.simple_spinner_dropdown_item,resources.getStringArray(R.array.jogadores)
        )
        binding.spinnerPlayer.adapter = adapterPayer
    }
    private fun configToolBar() {
        // Esconder a ActionBar se ela existir no tema.
        supportActionBar?.hide()
    }

    private fun startGame() {
        val battles: Int = when
                                   (binding.spinnerButtles.selectedItemPosition) {
            0 -> 1
            1 -> 3
            else -> 5
        }
        val multPlayer: Boolean = when (binding.spinnerPlayer.selectedItemPosition){
            1->true
            else ->false
        }

        val mIntent = Intent(this, WarActivity::class.java)
        mIntent.putExtra(Constants.KEY_PLAYER_1, binding.edittextPlayer1.text.toString())

        if (multPlayer){ //verifica se é um jogador ou dois
            mIntent.putExtra(Constants.KEY_PLAYER_2,binding.edittextPlayer2.text.toString())
            }
        else {
            //single player
            mIntent.putExtra(Constants.KEY_PLAYER_2,"MAQUINA")
            mIntent.putExtra(Constants.KEY_MULTPLAYER,1)

        }
        mIntent.putExtra(Constants.KEY_ROUNDS, battles)
        startActivity(mIntent)
    }
}
