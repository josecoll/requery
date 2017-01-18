package net.corda.schemas

import io.requery.Persistable
import io.requery.*
//import javax.persistence.Column
//import javax.persistence.Entity
//import javax.persistence.MappedSuperclass

//@MappedSuperclass
@Superclass interface PersistentState : Persistable {
        @get:Key
        @get:Column(name = "transaction_id", length = 64)
        var txId: String?

        @get:Key
        @get:Column(name = "output_index")
        var index: Int?
}

/**
 * Embedded [StateRef] representation used in state mapping.
 */
//@Embeddable
//data class PersistentStateRef(
//        @Column(name = "transaction_id", length = 64)
//        var txId: String?,
//
//        @Column(name = "output_index")
//        var index: Int?
//) : Serializable {
//    constructor(stateRef: StateRef) : this(stateRef.txhash.bytes.toHexString(), stateRef.index)
//}