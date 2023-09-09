package com.example.shaloonapp.view.util

import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

suspend fun getImgURLFromFirebase(imgPath: String):String {
    var imgURL : String =""
    if(imgPath!=""){
        val storage = Firebase.storage
        // Create a storage reference from our app
        val storageRef = storage.reference
        // Create a reference with an initial file path and name

        val pathReference = storageRef.child(imgPath)

        val uri = withContext(Dispatchers.IO) {
            pathReference.downloadUrl.await()
        }

        imgURL = uri.toString()
    }
    return imgURL
}