package br.com.zup.client.bcb.cadastra

data class BankAccout(
    val participant: String,
    val branch: String,
    val accountNumber: String,
    val accountType: AccountType
)