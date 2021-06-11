package br.com.zup.client.bcb.cadastra

import java.time.LocalDateTime

data class CadastraChavePixResponse(
    val keyType: String,
    val key: String,
    val bankAccount: BankAccout,
    val owner: Owner,
    val createdAt: LocalDateTime
)
