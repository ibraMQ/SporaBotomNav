package com.example.botomexample.ui.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.botomexample.Contact


class ListViewModel : ViewModel(){
    val contacts = arrayListOf(
        Contact("Luis","5537355896","Telcel"),
        Contact("Pepe","5558967425","Movi"),
        Contact("Javi","5598652415","Movi"),
        Contact("Seba","5505896546","Telcel"))
}