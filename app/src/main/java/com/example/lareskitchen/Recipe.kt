package com.example.lareskitchen

import android.media.Rating
import android.net.Uri
import java.time.LocalDateTime

class Recipe {

//    var RNameM = ""
//    var RDescM = ""
//    var RRemarkM = ""
//    var RRatingM = ""
//    var RfilePath = ""
//
//  constructor(RNameM:String , RDescM:String,RRemarkM:String,RRatingM:String,filePath:String){
//
//      this.RDescM = RDescM
//      this.RNameM = RNameM
//      this.RRemarkM = RRemarkM
//      this.RRatingM = RRatingM
//      this.RfilePath = filePath
//  }
var RRemarkM : String? = null
    var RRatingM : String? = null
    var RfilePath : String? = null
    var RNameM : String? = null
    var RDescM : String? = null
    var Chef : String? = null
    var date : String? = null
    var category :String?=null

    constructor(){}

    constructor(RNameM: String?, RDescM: String?, RRemarkM: String?, RRatingM: String?, RfilePath: String?, Chef :String?, Date:String?,Category:String?) {
        this.RNameM = RNameM
        this.RDescM = RDescM
        this.RRemarkM = RRemarkM
        this.RRatingM = RRatingM
        this.RfilePath = RfilePath
        this.Chef = Chef
        this.date = Date
        this.category = Category
    }





}