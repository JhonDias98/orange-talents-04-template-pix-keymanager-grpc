package br.com.zup.chavepix

import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface ChavePixRepository: JpaRepository<ChavePix, Long>{
    fun existsByChave(chave: String?): Boolean
}