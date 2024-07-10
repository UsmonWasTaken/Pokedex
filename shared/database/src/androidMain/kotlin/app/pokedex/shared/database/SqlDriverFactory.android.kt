package app.pokedex.shared.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

internal actual fun Scope.createSqlDriver(): SqlDriver {
    return AndroidSqliteDriver(
        schema = PokedexDatabase.Schema,
        context = androidContext(),
    )
}