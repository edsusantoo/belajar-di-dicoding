package com.edsusantoo.bismillah.academy.di

import android.app.Application
import com.edsusantoo.bismillah.academy.data.source.AcademyRepository
import com.edsusantoo.bismillah.academy.data.source.remote.RemoteRepository
import com.edsusantoo.bismillah.academy.utils.JsonHelper

class Injection {

    companion object {
        fun provideRepository(application: Application): AcademyRepository? {
            val remoteRepository: RemoteRepository? = RemoteRepository.getInstance(JsonHelper(application))

            return AcademyRepository.getInstance(remoteRepository!!)
        }
    }

}