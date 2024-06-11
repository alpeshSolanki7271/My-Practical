package com.mypractical.di

import android.content.Context
import com.mypractical.database.CompanyDao
import com.mypractical.database.EmployeeDao
import com.mypractical.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providePasswordDatabase(@ApplicationContext context: Context): MyDatabase {
        return MyDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCompanyDao(myDatabase: MyDatabase): CompanyDao {
        return myDatabase.companyDao()
    }

    @Singleton
    @Provides
    fun provideEmployeeDao(myDatabase: MyDatabase): EmployeeDao {
        return myDatabase.employeeDao()
    }

}