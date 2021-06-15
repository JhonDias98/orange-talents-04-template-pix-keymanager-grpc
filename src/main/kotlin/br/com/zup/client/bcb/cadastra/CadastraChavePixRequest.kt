package br.com.zup.client.bcb.cadastra

import br.com.zup.TipoDeChave
import br.com.zup.chavepix.ChavePix
import br.com.zup.chavepix.ContaAssociada
import br.com.zup.chavepix.registro.TipoDeChaveRegex

data class CadastraChavePixRequest(
    val keyType: TipoDeChaveRegex,
    val key: String,
    val bankAccount: BankAccount,
    val owner: Owner
) {

    companion object {

        fun of(chave: ChavePix): CadastraChavePixRequest {
            return CadastraChavePixRequest(
                keyType = chave.tipoDeChaveRegex,
                key = chave.chave,
                bankAccount = BankAccount(
                    participant = ContaAssociada.ITAU_UNIBANCO_ISPB,
                    branch = chave.conta.agencia,
                    accountNumber = chave.conta.numeroDaConta,
                    accountType = AccountType.by(chave.tipoDeConta)
                ),
                owner = Owner(
                    type = OwnerType.NATURAL_PERSON,
                    name = chave.conta.nomeDoTitular,
                    taxIdNumber = chave.conta.cpfDoTitular
                )
            )
        }
    }
}