package com.example.draganddropcompose.model

data class TargetModel(
    val title: String = "",
    val subTitle: String = "",
    val insideStringList: MutableList<String> = mutableListOf()
)