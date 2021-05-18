package com.example.lareskitchen

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference.child("images")
    private lateinit var database : FirebaseDatabase
    private lateinit var reference:DatabaseReference
    lateinit var firebaseRecyclerAdapter: FirebaseRecyclerAdapter<Recipe,MyAdapter.ViewHolder>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // add button
        addR.setOnClickListener {
            startActivity(Intent(applicationContext,AddRecipe::class.java))
        }
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("MSDRecipes")

        getData()
//        searchtextV.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//              if(searchtextV.text.toString()==""){
//                    getData()
//
//              }else{
//
//                  firebaseSearch(s.toString().toLowerCase())
//              }
//
//            }
//        })
    }


    private fun getData(){

        reference.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
               Log.e("cancel", p0.toString())
            }

            override fun onDataChange(p0: DataSnapshot) {
                var list=ArrayList<Recipe>()
                   for (data in p0.children){
                       var model = data.getValue(Recipe::class.java)
                       list.add(model as Recipe)
                   }

                if(list.size>0){
                    val adapter = MyAdapter(list,this@MainActivity)
                    recyclerView.adapter = adapter
                    R.layout.activity_main
                    MyAdapter::class.java

                }
            }

        })
    }



    private fun firebaseSearch(inputtxt: String){
       val query = FirebaseDatabase.getInstance().getReference()
               .child("Recipes")
               .orderByChild("rnameM")
               .startAt(inputtxt)
               .endAt(inputtxt + "\uf8ff")

        query.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Log.e("cancel", p0.toString())
            }
            //var list=ArrayList<Recipe>()

            override fun onDataChange(p0: DataSnapshot) {
                var  list=ArrayList<Recipe>()
                     list.clear()

                for (data in p0.children){
                    var model = data.getValue(Recipe::class.java)
                    list.add(model as Recipe)
                }

                if(list.size>0){
                    val adapter = MyAdapter(list,this@MainActivity)
                    recyclerView.adapter = adapter
                    R.layout.activity_main
                    MyAdapter::class.java

                }
            }

        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu,menu)
//        val menuItem = menu!!.findItem(R.id.search)
//        if(menuItem != null){
//            val searchView = menuItem.actionView as SearchView
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    if(newText.toString()==""){
//                        getData()
//
//                    }else{
//
//                        firebaseSearch(newText.toString().toLowerCase())
//                    }
//                    return true
//                }
//
//            })
//        }
//
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }
}
