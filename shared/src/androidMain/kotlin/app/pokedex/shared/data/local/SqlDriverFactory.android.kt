package app.pokedex.shared.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.pokedex.shared.database.PokedexDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

internal actual fun Scope.createSqlDriver(): SqlDriver {
    return AndroidSqliteDriver(
        schema = PokedexDatabase.Schema,
        context = androidContext(),
    )
}