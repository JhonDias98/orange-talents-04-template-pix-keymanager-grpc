package br.com.zup.client.bcb.cadastra

data class CadastraChavePixRequest(
    val keyType: String,
    val key: String,
    val bankAccount: BankAccout,
    val owner: Owner
)