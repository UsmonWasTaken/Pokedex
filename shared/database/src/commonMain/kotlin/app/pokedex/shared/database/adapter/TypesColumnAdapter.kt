package app.pokedex.shared.database.adapter

import app.cash.sqldelight.ColumnAdapter
import app.pokedex.shared.domain.model.PokemonInfo
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

internal object TypesColumnAdapter : ColumnAdapter<List<PokemonInfo.Type>, String> {

    override fun decode(databaseValue: String): List<PokemonInfo.Type> {
        return Json.decodeFromString(
            deserializer = ListSerializer(PokemonInfo.Type.serializer()),
            string = databaseValue,
        )
    }

    override fun encode(value: List<PokemonInfo.Type>): String {
        return Json.encodeToString(
            serializer = ListSerializer(PokemonInfo.Type.serializer()),
            value = value,
        )
    }
}
