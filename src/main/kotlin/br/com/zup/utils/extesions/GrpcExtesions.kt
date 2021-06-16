package br.com.zup.utils.extesions


import br.com.zup.ConsultaChavePixRequest
import br.com.zup.ConsultaChavePixRequest.FiltroCase.*
import br.com.zup.RegistraChavePixRequest
import br.com.zup.TipoDeChave.*
import br.com.zup.TipoDeConta
import br.com.zup.TipoDeConta.*
import br.com.zup.chavepix.NovaChavePix
import br.com.zup.chavepix.consulta.Filtro
import br.com.zup.chavepix.registro.TipoDeChaveRegex
import javax.validation.ConstraintViolationException
import javax.validation.Validator

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

fun ConsultaChavePixRequest.toModel(validator: Validator): Filtro {

    val filtro = when(filtroCase!!) {
        PIXID -> pixId.let {
            Filtro.PorPixId(clientId = it.clientId, pixId = it.pixId) // 1
        }
        CHAVE -> Filtro.PorChave(chave)
        FILTRO_NOT_SET -> Filtro.Invalido()
    }

    val violations = validator.validate(filtro)
    if (violations.isNotEmpty()) {
        throw ConstraintViolationException(violations);
    }

    return filtro
}