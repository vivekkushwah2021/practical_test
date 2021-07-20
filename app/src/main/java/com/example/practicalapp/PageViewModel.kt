package com.example.practicalapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicalapp.api.Repostiary
import com.example.practicalapp.model.UserModel

class PageViewModel :ViewModel() {

    var userData:LiveData<UserModel> = MutableLiveData<UserModel>()

    private fun getUser(page:Int){
        Repostiary.buildService()

    }

}