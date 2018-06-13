package asvid.github.io.roomapp.data.mappers

import asvid.github.io.roomapp.data.gist.Gist
import asvid.github.io.roomapp.data.gist.GistFields
import asvid.github.io.roomapp.model.GistModel
import io.realm.Realm

fun GistModel.toRealmModel(realm: Realm): Gist {
// TODO(11) mapowanie typu domenowego na typ bazy, w zależności czy obiekt ma ID
    if (this.id == null) {
        val gist = realm
                .use {
//                     TODO(12) obiekt nie ma ID, więc tworzymy nowe na podstawie największego znalezionego w bazie dla tej klasy
                    var maxId = it.where(Gist::class.java).max(GistFields.ID)?.toLong()
                    if (maxId == null) maxId = 0
//                    TODO(13) tworzymy pusty obiekt klasy Gist z nowym ID
                    it.createObject(Gist::class.java, ++maxId)
                }
// TODO(14) uzupełniamy pola obiektu bazy polami obiektu domenowego
        gist.description = this.description
        gist.starred = this.starred
        gist.date = this.date

        return gist
    } else {
        val gist = realm
                .use {
                    it.where(Gist::class.java)
                            .equalTo(GistFields.ID, this.id)
                            .findFirstAsync()
                }

        gist.description = this.description
        gist.starred = this.starred
        gist.date = this.date

        return gist
    }
}
// TODO(15) tworzymy model domenowy z uproszczonym obiektem Ownera - bez listy Gistów co doprowadzało do pętli mapowania
fun Gist.toModel(): GistModel {
    return GistModel(this.id, this.description, this.owner!!.first()!!.toSimpleModel(), this.starred, this.date)
}