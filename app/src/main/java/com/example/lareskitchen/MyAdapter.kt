package com.example.lareskitchen


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_details.view.*
import kotlinx.android.synthetic.main.row.view.*


class MyAdapter(var items:List<Recipe>, var context:Context) :RecyclerView.Adapter<MyAdapter.ViewHolder>(){






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int ) {
        val item = items[position]
        Glide.with(context).load(item.RfilePath).into(holder.imgView)
       // Picasso.get().load(item.RfilePath).into(holder.imgView)
        holder.rowTitel.text = item.RNameM
        holder.rowChef.text = item.Chef
       // holder.rowDate.text = item.date
     //   holder.rowCategory.text = item.category



        //row click
        holder.itemView.setOnClickListener{
           // var model  = items.get(position)
            var iTitel= item.RNameM
            var iImg = item.RfilePath
            var iDesc = item.RDescM
            var iRemark = item.RRemarkM
            var iChef = item.Chef
            var iRating = item.RRatingM
            var iDate = item.date
          //  var iCategory = item.category

            val intent = Intent(context,RecipeDetails::class.java)
            intent.putExtra("TitelD",iTitel)
            intent.putExtra("ImgD",iImg)
            intent.putExtra("DescD",iDesc)
            intent.putExtra("RemarkD",iRemark)
            intent.putExtra("ChefD",iChef)
            intent.putExtra("RatingD",iRating)
            intent.putExtra("DateD",iDate)
          //  intent.putExtra("categoryD",iCategory)


//            TitelD
//            var TitelD:String =
//            val intent = Intent(context,RecipeDetails::class.java)
//            intent.putExtra("iFName",gFName)
            context.startActivity(intent)
        }

    }
    class ViewHolder(itemView:View ) : RecyclerView.ViewHolder(itemView){
        val rowTitel = itemView.Title
        val rowChef = itemView.chef
      //  val rowDate = itemView.date
       // val rowCategory = itemView.category

        val imgView :ImageView = itemView.findViewById(R.id.rImg)
    }

}


