package br.com.zup.client.bcb.cadastra

import br.com.zup.chavepix.registro.TipoDeChaveRegex

data class CadastraChavePixRequest(
    val keyType: TipoDeChaveRegex,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner
)