package asvid.github.io.roomapp.data.migration

import asvid.github.io.roomapp.data.owner.OwnerFields
import io.realm.DynamicRealm
import io.realm.RealmObjectSchema
import timber.log.Timber

// TODO(19) konkretna migracja
class Version0Migration : VersionMigration {

    private val OWNER = "Owner"

    /************************************************
     * // Version 1
     * class Owner
     * String avatarUrl //added
     */
//    TODO(20) pobieramy schemę z DynamicRealm i dodajemy pole AvatarURL typu String
    override fun migrate(realm: DynamicRealm, oldVersion: Long) {
        if (oldVersion == 0L) {
            val ownerSchema = getObjectSchema(realm)
            ownerSchema!!.addField(OwnerFields.AVATAR_URL, String::class.java)

            Timber.d("migration complete $ownerSchema")
        }
    }

    private fun getObjectSchema(realm: DynamicRealm): RealmObjectSchema? {
        val schema = realm.schema
        return schema.get(OWNER)
    }
}