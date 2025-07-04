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

@Composable
fun MainScreen(innerPaddingValues: PaddingValues) {
    Column(modifier = Modifier.fillMaxSize().padding(innerPaddingValues).padding(20.dp)) {
        TargetScreen(Modifier.weight(1f).fillMaxWidth())

        Spacer(Modifier.height(30.dp))

        DragItemScreen(Modifier.weight(1f).fillMaxWidth())
    }
}