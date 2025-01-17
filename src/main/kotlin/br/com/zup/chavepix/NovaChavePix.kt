package br.com.zup.chavepix

import br.com.zup.TipoDeConta
import br.com.zup.chavepix.registro.TipoDeChaveRegex
import br.com.zup.client.bcb.cadastra.*
import br.com.zup.utils.validations.ValidPixKey
import br.com.zup.utils.validations.ValidUUID
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@ValidPixKey
@Introspected
data class NovaChavePix(
    @ValidUUID @field:NotBlank val clienteId: String,
    @field:NotNull val tipoDeChaveRegex: TipoDeChaveRegex?,
    @field:NotNull val tipoDeConta: TipoDeConta?,
    @field:Size(max = 77) val chave: String
) {

    fun toModel(conta: ContaAssociada): ChavePix{
        return ChavePix(
            clientId = UUID.fromString(this.clienteId),
            tipoDeChaveRegex = TipoDeChaveRegex.valueOf(this.tipoDeChaveRegex!!.name),
            tipoDeConta = TipoDeConta.valueOf(this.tipoDeConta!!.name),
            chave = if(this.tipoDeChaveRegex == TipoDeChaveRegex.RANDOM) UUID.randomUUID().toString() else this.chave,
            conta = conta
        )
    }

}