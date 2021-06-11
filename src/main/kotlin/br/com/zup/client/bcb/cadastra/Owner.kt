package br.com.zup.client.bcb.cadastra

data class Owner(
    val type: OwnerType,
    val name: String,
    val taxIdNumber: String
)