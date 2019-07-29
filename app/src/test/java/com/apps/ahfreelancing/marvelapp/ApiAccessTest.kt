package com.apps.ahfreelancing.marvelapp

import com.apps.ahfreelancing.marvelapp.data.cloud.CloudAccess
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

/**
 * Created by Ahmed Hassan on 7/29/2019.
 */
class ApiAccessTest {
    @Test
    fun cloudCharactersAreCorrect() {

        val cloudAccess = CloudAccess()
        val response = runBlocking { cloudAccess.getCharacters(1) }

        //Returned response can't be null
        assertNotNull(response)

        //data container must be null
        assertNotNull(response!!.dataContainer)

        //Results can't be null
        assertNotNull(response!!.dataContainer.results)

        //The returned page must contain only 10 elements
        assertEquals(response!!.dataContainer.results.size, 10)

    }


}