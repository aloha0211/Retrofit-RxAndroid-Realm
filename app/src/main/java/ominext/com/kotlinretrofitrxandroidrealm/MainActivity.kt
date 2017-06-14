package ominext.com.kotlinretrofitrxandroidrealm

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import ominext.com.kotlinretrofitrxandroidrealm.databinding.ActivityMainBinding
import ominext.com.kotlinretrofitrxandroidrealm.model.Github
import ominext.com.kotlinretrofitrxandroidrealm.rest.GithubApi
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.setDefaultConfiguration(RealmConfiguration.Builder(this).build())
        val realm: Realm = Realm.getDefaultInstance()
        val savedUser: Github? = RealmQuery.createQuery(realm, Github::class.java).findFirst()
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.user = savedUser

        GithubApi().gitHubService.getGithubUser("holuong0211")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    user ->
                    realm.beginTransaction()
                    realm.copyToRealmOrUpdate(user)
                    realm.commitTransaction()
                    binding.user = user
                }, {
                    error ->
                    Log.e("Error", error.message)
                })
    }
}
