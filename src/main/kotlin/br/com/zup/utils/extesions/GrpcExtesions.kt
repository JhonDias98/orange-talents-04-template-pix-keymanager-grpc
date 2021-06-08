package br.com.zup.utils.extesions

import br.com.zup.RegistraChavePixRequest
import br.com.zup.TipoDeChave.*
import br.com.zup.TipoDeConta
import br.com.zup.TipoDeConta.*
import br.com.zup.chavepix.NovaChavePix
import br.com.zup.chavepix.registro.TipoDeChaveRegex

fun RegistraChavePixRequest.toModel(): NovaChavePix {
    return NovaChavePix(
        clienteId = clientId,
        tipoDeChaveRegex = when(tipoDeChave){
            UNKNOWN_TIPO_CHAVE -> null
            else -> TipoDeChaveRegex.valueOf(tipoDeChave.name)
        },
        chave = chave,
        tipoDeConta = when(tipoDeConta){
            UNKNOWN_TIPO_CONTA -> null
            else -> TipoDeConta.valueOf(tipoDeConta.name)
        }
    )
}