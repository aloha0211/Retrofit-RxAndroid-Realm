package ominext.com.kotlinretrofitrxandroidrealm.rest

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by LuongHH on 6/14/2017.
 */
class GithubApi {

    val gson: Gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
        override fun shouldSkipClass(clazz: Class<*>?): Boolean {
            return clazz?.declaringClass == RealmObject::class.java
        }

        override fun shouldSkipField(f: FieldAttributes?): Boolean {
            return false
        }

    }).create()

    val retrofit: Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.github.com/")
            .build()

    val gitHubService: GithubService = retrofit.create(GithubService::class.java)
}