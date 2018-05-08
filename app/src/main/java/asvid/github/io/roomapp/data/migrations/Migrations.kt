package asvid.github.io.roomapp.data.migrations

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration
import asvid.github.io.roomapp.data.owner.OwnerEntity


val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE ${OwnerEntity.TABLE_NAME} ADD COLUMN ${OwnerEntity.AVATAR_URL} TEXT")
    }
}