package com.brisson.service

import com.brisson.DatabaseScope
import com.brisson.db.Addon
import com.brisson.transactionWithContext
import kotlinx.coroutines.withContext

interface AddonService {
    suspend fun getAllAddons(): List<Addon>
    suspend fun getAddonById(id: Long): Addon?
    suspend fun getAddonByTransportUrl(url: String): Addon?
    suspend fun saveAddon(transportUrl: String, manifestEncoded: String)
    suspend fun deleteAddonById(id: Long)
    suspend fun deleteAddonByTransportUrl(url: String)
}

fun DatabaseScope.addonService() = object : AddonService {
    private val log = logger.withTag("AddonService")
    private val query = databaseRef.addonQueries

    override suspend fun getAllAddons(): List<Addon> = withContext(backgroundDispatcher) {
        query.selectAllAddons().executeAsList()
    }

    override suspend fun getAddonById(id: Long): Addon? =
        query.selectAddonById(id).executeAsOneOrNull()


    override suspend fun getAddonByTransportUrl(url: String): Addon? =
        query.selectAddonByTransportUrl(url).executeAsOneOrNull()

    override suspend fun saveAddon(transportUrl: String, manifestEncoded: String) =
        databaseRef.transactionWithContext(backgroundDispatcher) {
            query.insertAddon(transportUrl, manifestEncoded)
        }.also {
            log.i { "Inserting $transportUrl addons into database" }
        }

    override suspend fun deleteAddonById(id: Long) =
        databaseRef.transactionWithContext(backgroundDispatcher) {
            query.deleteAddonById(id)
        }.also {
            log.i { "Deleting addon with id $id from database" }
        }

    override suspend fun deleteAddonByTransportUrl(url: String) =
        databaseRef.transactionWithContext(backgroundDispatcher) {
            query.deleteAddonByTransportUrl(url)
        }.also {
            log.i { "Deleting addon with transport url $url from database" }
        }
}
