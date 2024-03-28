package com.ubaya.advweek4160421056.model

data class Fighter(
    val id:Int?,
    val manufacturer:String?,
    val model:String?,
    val year:Int?,
    val max_speeed:String?,
    val url:String?,
    val armament:List<String>?,
    val dimension:FighterDimension?
)

data class FighterDimension(
    val length:Double?,
    val wingspan:Double?,
    val height:Double?
)
