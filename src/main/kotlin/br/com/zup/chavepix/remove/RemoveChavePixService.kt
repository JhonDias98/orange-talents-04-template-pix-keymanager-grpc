package br.com.zup.chavepix.remove

import br.com.zup.chavepix.ChavePixRepository
import br.com.zup.config.exceptions.ChavePixNaoEncontradaException
import br.com.zup.config.exceptions.ChavePixNaoEncontradaExceptionHandler
import br.com.zup.utils.validations.ValidUUID
import io.micronaut.validation.Validated
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import javax.validation.constraints.NotBlank

@Validated
@Singleton
class RemoveChavePixService(@Inject val repository: ChavePixRepository) {
    fun remove(@NotBlank @ValidUUID(message = "Cliente ID com formato invalido") clientId: String?,
               @NotBlank @ValidUUID(message = "Pix ID com formato invalido") pixId: String?
    ){
        val uuidPixId = UUID.fromString(pixId)
        val uuidClientId = UUID.fromString(clientId)

        val chave = repository.findByPixIdAndClientId(uuidPixId, uuidClientId)
            .orElseThrow{ChavePixNaoEncontradaException("Chave Pix não encontrada ou não pertence ao cliente")}
        repository.delete(chave)
    }

}