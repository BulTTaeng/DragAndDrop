package com.example.draganddropcompose.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.draganddropcompose.ui.viewModel.MainViewModel

@Composable
fun MainScreen(
    innerPaddingValues: PaddingValues,
    viewModel: MainViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize().padding(innerPaddingValues).padding(20.dp)) {
        TargetScreen(Modifier.weight(1f).fillMaxWidth(), viewModel.targetList) { idx, str ->
            viewModel.addTargetList(idx, str)
        }

        Spacer(Modifier.height(30.dp))

        DragItemScreen(Modifier.weight(1f).fillMaxWidth(), viewModel.dragSourceList)
    }
}