package br.com.zup.client.bcb.consulta

import br.com.zup.Instituicoes
import br.com.zup.TipoDeChave
import br.com.zup.TipoDeConta
import br.com.zup.chavepix.ContaAssociada
import br.com.zup.chavepix.consulta.ChavePixInfo
import br.com.zup.chavepix.registro.TipoDeChaveRegex
import br.com.zup.client.bcb.cadastra.AccountType
import br.com.zup.client.bcb.cadastra.BankAccount
import br.com.zup.client.bcb.cadastra.Owner
import br.com.zup.client.itau.InstituicaoResponse
import java.time.LocalDateTime

class ConsultaChavePixResponse(
    val keyType: TipoDeChaveRegex,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner,
    val createdAt: LocalDateTime
) {
    fun toModel(): ChavePixInfo {
        return ChavePixInfo(
            tipoDeChave = keyType,
            chave = this.key,
            tipoDeConta = when(this.bankAccount.accountType) {
                AccountType.CACC -> TipoDeConta.CONTA_CORRENTE
                AccountType.SVGS -> TipoDeConta.CONTA_POUPANCA
            },
            conta = ContaAssociada(
                instituicao = Instituicoes.nome(bankAccount.participant),
                nomeDoTitular = owner.name,
                cpfDoTitular = owner.taxIdNumber,
                agencia = bankAccount.branch,
                numeroDaConta = bankAccount.accountNumber,
                ispb = "00000"
            )

        )
    }
}
