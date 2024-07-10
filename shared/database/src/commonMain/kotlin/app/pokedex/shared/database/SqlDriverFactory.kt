package app.pokedex.shared.database

import app.cash.sqldelight.db.SqlDriver
import org.koin.core.scope.Scope

internal expect fun Scope.createSqlDriver(): SqlDriver

internal val PokedexDatabase.Companion.name: String
    inline get() = "pokedex.db"
