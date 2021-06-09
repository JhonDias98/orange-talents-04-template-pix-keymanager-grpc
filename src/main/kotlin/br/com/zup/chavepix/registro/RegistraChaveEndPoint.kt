package br.com.zup.chavepix.registro

import br.com.zup.KeymanagerRegistraGrpcServiceGrpc
import br.com.zup.RegistraChavePixRequest
import br.com.zup.RegistraChavePixResponse
import br.com.zup.config.grpc.interceptor.ErrorHandler
import io.grpc.stub.StreamObserver
import br.com.zup.utils.extesions.toModel
import javax.inject.Inject
import javax.inject.Singleton

@ErrorHandler
@Singleton
class RegistraChaveEndPoint(@Inject private val service: NovaChavePixService)
    : KeymanagerRegistraGrpcServiceGrpc.KeymanagerRegistraGrpcServiceImplBase() {
    override fun registra(
        request: RegistraChavePixRequest?,
        responseObserver: StreamObserver<RegistraChavePixResponse>?
    ) {
        println(request)
        val novaChave = request!!.toModel()
        val service = service.registra(novaChave)
        responseObserver?.onNext(RegistraChavePixResponse.newBuilder()
            .setClientId(service.clientId.toString())
            .setPixId(service.pixId.toString())
            .build())
        responseObserver?.onCompleted()
    }
}