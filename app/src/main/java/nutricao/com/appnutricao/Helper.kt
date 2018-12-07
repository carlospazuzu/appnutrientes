package nutricao.com.appnutricao

class Helper private constructor(){

    companion object {

        val txt_sedentario = "Não faz atividades físicas periodicamente e passa grande parte do dia sentado."
        val txt_levemente_ativo = "Pratica exercícios leves, com uma frequência que varia de 1 a 3 dias na semana."
        val txt_moderadamente_ativo = "Pratica exercícios com intensidade moderada e frequência entre 4 e 7 dias na semana."
        val txt_muito_ativo = "Realiza exercícios intensos todos os dias ou atividades moderadas 2x ao dia."

        val SEDENTARIO: Int = 0
        val LEVEMENTE_ATIVO: Int = 1
        val MODERADAMENTE_ATIVO: Int = 2
        val MUITO_ATIVO: Int = 3

        fun getInstance(): Helper
        {
            val instance = Helper()
            return instance
        }

        fun getEstiloDescr(estilo: Int): String
        {
            when(estilo)
            {
                SEDENTARIO -> return txt_sedentario
                LEVEMENTE_ATIVO -> return txt_levemente_ativo
                MODERADAMENTE_ATIVO -> return txt_moderadamente_ativo
                MUITO_ATIVO -> return txt_muito_ativo
            }

            return ""
        }

        fun getEstiloCalc(estilo: Int, altura: Double, peso: Double): Double
        {
            val tmb = ((11 * peso) + (16 * altura)) + 901
            when(estilo)
            {
                SEDENTARIO -> return tmb * 1.0
                LEVEMENTE_ATIVO -> return tmb * 1.11
                MODERADAMENTE_ATIVO -> return tmb * 1.25
                MUITO_ATIVO -> return tmb * 1.48
            }

            return 0.0
        }
    }
}