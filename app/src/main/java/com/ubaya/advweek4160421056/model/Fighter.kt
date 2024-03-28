package com.ubaya.advweek4160421056.model

data class Fighter(
    val id:Int?,
    val manufacturer:String?,
    val model:String?,
    val year:Int?,
    val max_speed:String?,
    val url:String?,
    val armament:List<String>?,
    val dimension:FighterDimension?
)

data class FighterDimension(
    val length:String?,
    val wingspan:String?,
    val height:String?
)
