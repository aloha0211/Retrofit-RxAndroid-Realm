package ominext.com.kotlinretrofitrxandroidrealm.rest

import ominext.com.kotlinretrofitrxandroidrealm.model.Github
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by LuongHH on 6/14/2017.
 */
interface GithubService {

    @GET("users/{username}")
    fun getGithubUser(@Path("username") username: String): Observable<Github>
}