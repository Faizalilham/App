package com.faizal.newsapp.domain.usecases.datastore

import com.faizal.newsapp.domain.datasource.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManger: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}