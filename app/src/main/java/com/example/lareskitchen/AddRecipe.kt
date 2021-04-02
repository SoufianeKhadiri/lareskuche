package com.example.lareskitchen

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.view.get
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_add_recipe.*
import kotlinx.android.synthetic.main.row.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AddRecipe : AppCompatActivity() {

    lateinit var storageReference : StorageReference

    private lateinit var database : FirebaseDatabase
    private lateinit var reference:DatabaseReference
    lateinit var filePath : Uri
    lateinit var url : String
    lateinit var option :Spinner
    lateinit var CategoryV :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)
      url = ""
//      storageReference = FirebaseStorage.getInstance().getReference("images")
      database = FirebaseDatabase.getInstance()
      reference = database.getReference("Recipes")
        option = findViewById(R.id.spinner) as Spinner
        val options = arrayOf("Vorspeise","Hauptspeise","Dessert","Kuchen","Gebäck","kekse")
        option.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        option.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 CategoryV = options.get(position)
            }

        }

        AddR.setOnClickListener{

            uploadImg()

        }

        RImgBtn.setOnClickListener{
            startFileChooser()
        }

        RecipesList.setOnClickListener {
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }
    }

    private fun sendData(url:String){
        var NameV = RName.text.toString()
        var DescV = RDesc.text.toString()
        var RemarkV = RRemarks.text.toString()
        var RatingV = RRatingBar.rating.toString()
        var ChefV = cheff.text.toString()
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        val currentDateAndTime: String = simpleDateFormat.format(Date())







        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              CategoryV  = spinner.get(position).toString()
            }

        }







         if(NameV.isNotEmpty() && DescV.isNotEmpty() && ChefV.isNotEmpty() && RatingV.isNotEmpty() ){

             var model = Recipe(NameV.toLowerCase(),DescV.toLowerCase(),RemarkV.toLowerCase(),RatingV,url,ChefV.toLowerCase(),currentDateAndTime,CategoryV.toLowerCase())
            //var id = reference.push().key

             reference.child(NameV.toLowerCase()).setValue(model)
            RName.setText("")
            RDesc.setText("")
            RRemarks.setText("")
            cheff.setText("")

        }else{
            Toast.makeText(applicationContext,"all Field Required",Toast.LENGTH_SHORT).show()
        }
    }



    private fun startFileChooser() {
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Choose Picture"),111)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 111 && resultCode == Activity.RESULT_OK && data != null) {
            filePath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver , filePath)
            Img.setImageBitmap(bitmap)


//            val uploadTask = storageReference!!.putFile(data!!.data!!)
//            val task = uploadTask.continueWithTask{
//                    task ->
//                if(!task.isSuccessful){
//                    Toast.makeText(applicationContext,"Failed",Toast.LENGTH_SHORT).show()
//                }
//                storageReference!!.downloadUrl
//            }.addOnCompleteListener {  task ->
//                if(task.isSuccessful){
//                    val downloadUri = task.result
//                    val url = downloadUri!!.toString()
//                    Log.d("DiRectLINK",url)
//                }
//
//            }
        }

    }
    private fun  uploadImg(){
        var NameV = RName.text.toString()
         if(filePath != null){
             var pd = ProgressDialog(this)
             pd.setTitle("Uploadig")
             pd.show()
             storageReference = FirebaseStorage.getInstance().getReference("images/"+NameV)
             val uploadTask = storageReference!!.putFile(filePath)


             val task = uploadTask.continueWithTask{
                 task ->
                 if(!task.isSuccessful){
                     Toast.makeText(applicationContext,"Failed",Toast.LENGTH_SHORT).show()
                 }
                 storageReference!!.downloadUrl
             }.addOnCompleteListener {  task ->
                 if(task.isSuccessful){
                     val downloadUri = task.result
                      url = downloadUri!!.toString()

                     sendData(url)
                     pd.cancel()

                 }

             }

//             var imageRef:StorageReference = FirebaseStorage.getInstance().reference.child("images/"+imageName)
//
//             imageRef.putFile(filePath)
//
//
//                 .addOnSuccessListener {
//                     pd.cancel()
////                     val obj = Recipe()
////                     obj.RfilePath = filePath.toString()
//                     Toast.makeText(applicationContext,"Recipe Added",Toast.LENGTH_SHORT).show()
//                    }
//
         }

    }


}