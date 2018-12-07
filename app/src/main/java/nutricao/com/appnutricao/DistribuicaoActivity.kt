package nutricao.com.appnutricao

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_distribuicao.*

class DistribuicaoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distribuicao)

        val proteinas = (intent.getDoubleExtra(MainActivity.TMB, 0.0) * 0.15) / 4
        val carboidratos = (intent.getDoubleExtra(MainActivity.TMB, 0.0) * 0.6) / 4
        val gorduras = (intent.getDoubleExtra(MainActivity.TMB, 0.0) * 0.25) / 9

        txt_nome.text = intent.getStringExtra(MainActivity.USERNAME)

        txt_proteinas.text = "Proteínas: ${String.format("%.3f", proteinas)} cal"
        txt_gorduras.text = "Gorduras: ${String.format("%.3f", gorduras)} cal"
        txt_carboidratos.text = "Carboidratos: ${String.format("%.3f", carboidratos)} cal"
    }
}
