package app.pokedex.shared.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.core.scope.Scope
import java.io.File

internal actual fun Scope.createSqlDriver(): SqlDriver {
    val databasePath = File(System.getProperty("java.io.tmpdir"), PokedexDatabase.name).path
    return JdbcSqliteDriver(
        url = "jdbc:sqlite:$databasePath",
        schema = PokedexDatabase.Schema,
    )
}