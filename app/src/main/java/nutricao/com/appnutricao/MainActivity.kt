package nutricao.com.appnutricao

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var altura = 0.0
    var peso = 0.0
    var tmb = 0.00
    var nome = ""

    companion object {
        val USERNAME: String = "USERNAME"
        val GET_USER_DATA = 1
        val ALTURA = "ALTURA"
        val PESO = "PESO"
        val TMB = "TMB"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT < 16)
        {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        window.decorView.setBackgroundColor(resources.getColor(R.color.colorBG))

        btn_continuar.setOnClickListener {
            val intent = Intent(this, DadosActivity::class.java)
            intent.putExtra(USERNAME, edt_username.text.toString())

            startActivityForResult(intent, MainActivity.GET_USER_DATA)

        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.dropdown_entries, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val ctx = this

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                when(position)
                {
                    0 -> updateInfo(Helper.SEDENTARIO)
                    1 -> updateInfo(Helper.LEVEMENTE_ATIVO)
                    2 -> updateInfo(Helper.MODERADAMENTE_ATIVO)
                    3 -> updateInfo(Helper.MUITO_ATIVO)
                }
            }
        }

        btn_dist.setOnClickListener{
            val intent = Intent(this, DistribuicaoActivity::class.java)
            intent.putExtra(MainActivity.TMB, tmb)
            intent.putExtra(MainActivity.USERNAME, nome)

            startActivity(intent)
        }
    }

    fun updateInfo(estilo: Int)
    {
        val novo_tbm = Helper.getEstiloCalc(estilo, altura, peso)
        tmb = novo_tbm
        txt_tmb.text = "TMB: ${novo_tbm}"
        txt_descr_estilo.text = Helper.getEstiloDescr(estilo)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == MainActivity.GET_USER_DATA)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                altura = data!!.getDoubleExtra(DadosActivity.ALTURA, 0.0)
                peso = data.getDoubleExtra(DadosActivity.PESO, 0.0)
                nome = data.getStringExtra(DadosActivity.NOME_USUARIO)
                tmb = ((11.3 * peso) + (16 * altura)) + 901

                txt_digite_seu_nome.visibility = View.GONE
                edt_username.visibility = View.GONE
                btn_continuar.visibility = View.GONE

                txt_bemvindo.text = nome
                txt_altura.text = "$altura cm"
                txt_peso.text = "$peso Kg"

                txt_tmb.text = "TMB: ${tmb}" // calculo do estilo sedentario
                txt_descr_estilo.text = "Não faz atividades físicas periodicamente e passa grande parte do dia sentado."

                img_altura.visibility = View.VISIBLE
                img_peso.visibility = View.VISIBLE
                txt_altura.visibility = View.VISIBLE
                txt_peso.visibility = View.VISIBLE
                spinner.visibility = View.VISIBLE
                txt_tmb.visibility = View.VISIBLE
                txt_descr_estilo.visibility = View.VISIBLE
                btn_dist.visibility = View.VISIBLE


            }
        }

    }

}
