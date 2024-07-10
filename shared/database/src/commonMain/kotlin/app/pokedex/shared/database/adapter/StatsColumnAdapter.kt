package app.pokedex.shared.database.adapter

import app.cash.sqldelight.ColumnAdapter
import app.pokedex.shared.domain.model.PokemonInfo
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

internal object StatsColumnAdapter : ColumnAdapter<List<PokemonInfo.Stat>, String> {

    override fun decode(databaseValue: String): List<PokemonInfo.Stat> {
        return Json.decodeFromString(
            deserializer = ListSerializer(PokemonInfo.Stat.serializer()),
            string = databaseValue,
        )
    }

    override fun encode(value: List<PokemonInfo.Stat>): String {
        return Json.encodeToString(
            serializer = ListSerializer(PokemonInfo.Stat.serializer()),
            value = value,
        )
    }
}