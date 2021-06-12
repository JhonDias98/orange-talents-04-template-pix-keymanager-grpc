package br.com.zup.client.bcb.cadastra

import br.com.zup.chavepix.registro.TipoDeChaveRegex
import java.time.LocalDateTime

data class CadastraChavePixResponse(
    val keyType: TipoDeChaveRegex,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
)
