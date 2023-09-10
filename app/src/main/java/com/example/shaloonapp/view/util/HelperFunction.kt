package com.example.shaloonapp.view.util

import com.example.shaloonapp.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

suspend fun getImgURLFromFirebase(imgPath: String):String {
    var imgURL : String =""
    if(imgPath!=""){
        // Create a storage reference from our app
        val storageRef = Firebase.storage.reference
        // Create a reference with an initial file path and name
        val pathReference = try {
            storageRef.child(imgPath)
        } catch (e: StorageException ) {
            // Handle storage exception gracefully, e.g., use a default image
            storageRef.child("img/no_img_error.jpg")
        } catch (e: Exception){
            return  R.drawable.no_img_error.toString()
        }

        val uri = withContext(Dispatchers.IO) {
            pathReference.downloadUrl.await()
        }
        imgURL = uri.toString()
    }
    return imgURL
}