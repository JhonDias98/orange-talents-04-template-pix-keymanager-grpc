package br.com.zup.chavepix.registro

import br.com.zup.chavepix.ChavePix
import br.com.zup.chavepix.ChavePixRepository
import br.com.zup.chavepix.NovaChavePix
import br.com.zup.client.bcb.BancoCentralClient
import br.com.zup.client.bcb.cadastra.CadastraChavePixRequest
import br.com.zup.client.itau.ItauClient
import br.com.zup.config.exceptions.ChavePixExistenteException
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChavePixService(@Inject val repository: ChavePixRepository,
                          @Inject val itauClient: ItauClient,
                          @Inject val bcbClient: BancoCentralClient
) {
    private val Logger = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun registra(@Valid novaChavePix: NovaChavePix?):ChavePix {

        //Verifica se a chave ja existe no banco
        if(repository.existsByChave(novaChavePix?.chave)) {
            throw ChavePixExistenteException("Chave ja cadastrada")
        }

        //Consulta no sistema do ERP do ITAU Client
        val itauClientResponse = itauClient.buscaContaPorTipo(novaChavePix?.clienteId!!, novaChavePix.tipoDeConta!!.name)
        val conta = itauClientResponse.body()?.toModel() ?: throw IllegalStateException("Cliente nao encontrato no itau")
        Logger.info("Busca pela conta concluído com sucesso")

        //Cadastrando a chave no Banco Central do Brasil (BCB)
        val bcbResponse = bcbClient.create(novaChavePix.toBcb(conta))
        Logger.info("Registrando chave Pix no Banco Central do Brasil")

        //Salva no banco de dados
        val novaChave = novaChavePix.toModel(conta, bcbResponse.body()!!)
        repository.save(novaChave)
        Logger.info("Chave Pix salva com sucesso no sistema")
        return novaChave
    }
}