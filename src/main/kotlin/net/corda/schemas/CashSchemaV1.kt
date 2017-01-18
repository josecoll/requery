package net.corda.schemas

import javax.persistence.*

//object CashSchema

object CashSchemaV1 {
    //    : MappedSchema(schemaFamily = CashSchema.javaClass, version = 1, mappedTypes = listOf(PersistentCashState::class.java)) {
    @Entity
    @Table(name = "cash_states")
    interface PersistentCashState : PersistentState {

        @get:Column(name = "owner_key")
        var owner: String

        @get:Column(name = "pennies")
        var pennies: Long

        @get:Column(name = "ccy_code", length = 3)
        var currency: String

        @get:Column(name = "issuer_key")
        var issuerParty: String

        @get:Column(name = "issuer_ref")
        var issuerRef: ByteArray
    }
}

