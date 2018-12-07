package nutricao.com.appnutricao

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_dados.*
import kotlinx.android.synthetic.main.activity_dados.view.*
import kotlinx.android.synthetic.main.activity_main.*

class DadosActivity : AppCompatActivity() {

    companion object {
        val ALTURA = "ALTURA"
        val PESO = "PESO"
        val NOME_USUARIO = "NOME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados)

        val nomeUsuario = intent.getStringExtra(MainActivity.USERNAME)
        // Log.i("DEBUG", "Nome do Usuario passado via Intent: " + nomeUsuario)

        textview_usuario.text = nomeUsuario

        btn_finalizar.setOnClickListener {
            // setResult(Activity.RESULT_OK)

            try
            {
                val altura = editText.text.toString().toDouble()
                val peso = editText2.text.toString().toDouble()

                intent.putExtra(DadosActivity.ALTURA, altura)
                intent.putExtra(DadosActivity.PESO, peso)
                intent.putExtra(DadosActivity.NOME_USUARIO, nomeUsuario)

                setResult(Activity.RESULT_OK, intent)
                finish()

            }catch (e: Exception)
            {
                Toast.makeText(this, "Por Favor, digite valores numéricos.", Toast.LENGTH_LONG).show()
            }

        }
    }

}
