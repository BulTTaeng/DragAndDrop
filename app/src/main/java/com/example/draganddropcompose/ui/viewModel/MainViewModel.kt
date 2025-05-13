package com.example.draganddropcompose.ui.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.draganddropcompose.model.DragSource
import com.example.draganddropcompose.model.TargetModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _dragSourceList = mutableStateListOf(
        DragSource("aaaaaaaaaaa"),
        DragSource("bbbbbbbbbbb"),
        DragSource("ccccccccccc")
    )
    val dragSourceList = _dragSourceList

    private val _targetList = mutableStateListOf(
        TargetModel("first", "first subtitle"),
        TargetModel("second", "second subtitle"),
        TargetModel("third", "third subtitle")
    )
    val targetList = _targetList

    fun addTargetList(index: Int, dragSourceTitle: String) {
        targetList.getOrNull(index)?.insideStringList?.add(dragSourceTitle)
    }

}