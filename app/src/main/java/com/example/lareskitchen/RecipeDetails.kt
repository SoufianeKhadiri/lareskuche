package com.example.lareskitchen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recipe_details.*

class RecipeDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
//        val actionBar : ActionBar? = supportActionBar
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
//        actionBar!!.setDisplayShowCustomEnabled(true)

        var intent = intent

        val TitelD = intent.getStringExtra("TitelD")
        val ImagD = intent.getStringExtra("ImgD")
        val DescD = intent.getStringExtra("DescD")
        val RemarkD = intent.getStringExtra("RemarkD")
        val ChefD = intent.getStringExtra("ChefD")
        val DateD = intent.getStringExtra("DateD")
        val IRating = intent.getStringExtra("RatingD")
       // val CategoryD = intent.getStringExtra("categoryD")






        titelD.text = TitelD
        Glide.with(this).load(ImagD).into(imgD)
        descD.text = DescD
        remarkD.text = RemarkD
        chefD.text = "Chef: "+ChefD
        dateD.text = DateD
        if (IRating != null) {
            ratingD.rating = IRating.toFloat()
        }
       // category.text = "Category :"+ CategoryD

    }
}