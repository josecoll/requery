package net.corda.schemas

import io.requery.kotlin.eq
import io.requery.sql.KotlinConfiguration
import io.requery.sql.KotlinEntityDataStore
import io.requery.sql.SchemaModifier
import io.requery.sql.TableCreationMode
import org.h2.jdbcx.JdbcDataSource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by josecoll on 13/01/2017.
 */
class CashSchemaTest {
    var instance : KotlinEntityDataStore<Any>? = null
    val data : KotlinEntityDataStore<Any> get() = instance!!

    @Before
    fun setup() {
        val model = Models.SCHEMAS
        val dataSource = JdbcDataSource()
        dataSource.setUrl("jdbc:h2:~/testh2")
        dataSource.user = "sa"
        dataSource.password = "sa"

        val configuration = KotlinConfiguration(
                dataSource = dataSource,
                model = model,
                statementCacheSize = 0,
                useDefaultLogging = true)
        instance = KotlinEntityDataStore<Any>(configuration)
        val tables = SchemaModifier(configuration)
        val mode = TableCreationMode.DROP_CREATE
        tables.createTables(mode)
    }

    @Test
    fun testInsert() {
        val state = PersistentCashStateEntity()
        state.txId = "12345"
        state.index = 0
        data.invoke {
            insert(state)
            val result = select(CashSchemaV1.PersistentCashState::class) where (CashSchemaV1.PersistentCashState::txId eq state.txId) limit 10
            assertEquals(result.get().first().txId, state.txId)
        }
    }
}