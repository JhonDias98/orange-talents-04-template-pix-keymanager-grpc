package br.com.zup.chavepix.consulta

import br.com.zup.ConsultaChavePixResponse
import br.com.zup.TipoDeChave
import br.com.zup.TipoDeConta
import com.google.protobuf.Timestamp
import java.time.ZoneId

class ConsultaChavePixResponseConverter {
    fun convert(chaveInfo: ChavePixInfo): ConsultaChavePixResponse {
        return ConsultaChavePixResponse.newBuilder()
            .setClientId(chaveInfo.clientId?.toString() ?: "") // Protobuf usa "" como default value para String
            .setPixId(chaveInfo.pixId?.toString() ?: "") // Protobuf usa "" como default value para String
            .setChave(
                ConsultaChavePixResponse.ChavePix // 1
                    .newBuilder()
                    .setTipoDeChave(TipoDeChave.valueOf(chaveInfo.tipoDeChave.name)) // 2
                    .setChave(chaveInfo.chave)
                    .setConta(
                        ConsultaChavePixResponse.ChavePix.ContaInfo.newBuilder() // 1
                            .setTipoDeConta(TipoDeConta.valueOf(chaveInfo.tipoDeConta.name)) // 2
                            .setInstituicao(chaveInfo.conta.instituicao) // 1 (Conta)
                            .setNomeDoTitular(chaveInfo.conta.nomeDoTitular)
                            .setCpfDoTitular(chaveInfo.conta.cpfDoTitular)
                            .setAgencia(chaveInfo.conta.agencia)
                            .setNumeroDaConta(chaveInfo.conta.numeroDaConta)
                            .build()
                    )
                    .setCriadaEm(chaveInfo.registradaEm.let {
                        val createdAt = it.atZone(ZoneId.of("UTC")).toInstant()
                        Timestamp.newBuilder()
                            .setSeconds(createdAt.epochSecond)
                            .setNanos(createdAt.nano)
                            .build()
                    })
            )
            .build()
    }
}