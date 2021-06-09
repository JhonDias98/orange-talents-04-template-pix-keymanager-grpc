package br.com.zup.chavepix.remove

import br.com.zup.KeymanagerRemoveGrpcServiceGrpc
import br.com.zup.RemoveChavePixRequest
import br.com.zup.RemoveChavePixResponse
import br.com.zup.config.grpc.interceptor.ErrorHandler
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@ErrorHandler
@Singleton
class RemoveChavePixEndPoint(@Inject private val service: RemoveChavePixService)
    : KeymanagerRemoveGrpcServiceGrpc.KeymanagerRemoveGrpcServiceImplBase(){

    override fun remove(request: RemoveChavePixRequest?, responseObserver: StreamObserver<RemoveChavePixResponse>?) {
        service.remove(request?.clientId, request?.pixId)

        responseObserver?.onNext(RemoveChavePixResponse.newBuilder()
            .setClientId(request?.clientId)
            .setPixId(request?.pixId)
            .build())
        responseObserver?.onCompleted()
    }
}