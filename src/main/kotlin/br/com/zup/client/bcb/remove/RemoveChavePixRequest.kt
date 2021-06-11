package br.com.zup.client.bcb.remove

data class RemoveChavePixRequest(
    val key: String,
    val participant: String = "60701190"
)
