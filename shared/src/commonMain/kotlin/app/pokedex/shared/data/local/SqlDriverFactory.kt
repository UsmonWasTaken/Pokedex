package app.pokedex.shared.data.local

import app.cash.sqldelight.db.SqlDriver
import app.pokedex.shared.database.PokedexDatabase
import org.koin.core.scope.Scope

internal expect fun Scope.sqlDriverFactory(): SqlDriver

internal val PokedexDatabase.Companion.name: String
    inline get() = "pokedex.db"
