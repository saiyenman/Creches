package com.intellitech.creches.models

import java.lang.reflect.Constructor

data class Other(
    val descritption: String,
    val timing: String,
    val title: String,
    val to:List<String>,
    val done:Boolean){
constructor():this("","","",listOf(),false)
}