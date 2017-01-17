package net.corda.schemas

import javax.persistence.*

//object CashSchema

object CashSchemaV1 {
//    : MappedSchema(schemaFamily = CashSchema.javaClass, version = 1, mappedTypes = listOf(PersistentCashState::class.java)) {
    @Entity
    @Table(name = "cash_states")
    @NamedQueries(
            NamedQuery(name="cash.findByCurrency", query="SELECT c FROM CashSchemaV1\$PersistentCashState c WHERE c.currency = ?1")
    )
    class PersistentCashState(
            @Column(name = "owner_key")
            var owner: String,

            @Column(name = "pennies")
            var pennies: Long,

            @Column(name = "ccy_code", length = 3)
            var currency: String,

            @Column(name = "issuer_key")
            var issuerParty: String,

            @Column(name = "issuer_ref")
            var issuerRef: ByteArray
    ) //: PersistentState()
}
