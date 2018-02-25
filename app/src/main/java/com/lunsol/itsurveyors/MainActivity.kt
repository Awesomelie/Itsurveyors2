package com.lunsol.itsurveyors

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.annotation.RequiresApi

class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{

            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }
                requestPermissions(
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        1
                )
            }
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "image/*"
                addCategory(Intent.CATEGORY_OPENABLE)
            }
            startActivityForResult(intent, 6542)
            Toast.makeText(this, "Open Garally", Toast.LENGTH_LONG).show()

        }
        
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 6542) {
            if (resultCode != Activity.RESULT_OK) {
                return
            }
            val imageData = data?.data ?: return
            val docID = DocumentsContract.getDocumentId(imageData)
            val strSplittedDocId = docID.split(":")
            val strId = strSplittedDocId[strSplittedDocId.size - 1]
            val cursor = contentResolver.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    arrayOf(MediaStore.MediaColumns.DATA),
                    "_id=?",
                    arrayOf<String>(strId),
                    null
            )
            cursor.moveToFirst()
            val imageURL:String = cursor.getString(0)

            val intent: Intent = Intent(this, DrawViewActivity::class.java)
            intent.putExtra("path", imageURL)
            startActivity(intent)
    }
    }

}