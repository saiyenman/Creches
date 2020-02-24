package com.intellitech.creches.models

class DayMenu (val meal1: List<Meal>,
               val meal2: List<Meal>,val meal3:List<Meal>){
    constructor(): this(listOf(), listOf(), listOf())

}