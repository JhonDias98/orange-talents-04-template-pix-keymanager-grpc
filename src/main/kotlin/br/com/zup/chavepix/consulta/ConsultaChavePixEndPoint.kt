package br.com.zup.chavepix.consulta

import br.com.zup.ConsultaChavePixRequest
import br.com.zup.ConsultaChavePixResponse
import br.com.zup.KeymanagerConsultaGrpcServiceGrpc
import br.com.zup.chavepix.ChavePixRepository
import br.com.zup.client.bcb.BancoCentralClient
import br.com.zup.config.grpc.interceptor.ErrorHandler
import br.com.zup.utils.extesions.toModel
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton
import javax.validation.Validator

@ErrorHandler
@Singleton
class ConsultaChavePixEndPoint(
    @Inject private val repository: ChavePixRepository,
    @Inject private val bcbClient: BancoCentralClient,
    @Inject private val validator: Validator
) : KeymanagerConsultaGrpcServiceGrpc.KeymanagerConsultaGrpcServiceImplBase() {
    override fun consulta(
        request: ConsultaChavePixRequest,
        responseObserver: StreamObserver<ConsultaChavePixResponse>
    ) {
        val filtro = request.toModel(validator)
        val chaveInfo = filtro.filtra(repository, bcbClient)

        responseObserver.onNext(ConsultaChavePixResponseConverter().convert(chaveInfo))
        responseObserver.onCompleted()
    }
}