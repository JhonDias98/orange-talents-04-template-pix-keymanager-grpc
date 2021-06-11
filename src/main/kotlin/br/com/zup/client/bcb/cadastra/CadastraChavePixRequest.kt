package br.com.zup.client.bcb.cadastra

data class CadastraChavePixRequest(
    val keyType: String,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner
)