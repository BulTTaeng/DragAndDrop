package com.example.draganddropcompose.screen

import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.draganddropcompose.model.DragSource


@Composable
fun DragItemScreen(modifier: Modifier, dragSourceList: List<DragSource>) {
    LazyColumn(modifier) {
        dragSourceList.forEach{
            item { DragSourceUi(it) }
            item { Spacer(Modifier.height(10.dp)) }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DragSourceUi(dragSource: DragSource){
    Column(
        Modifier
            .wrapContentWidth()
            .height(50.dp)
            .dragAndDropSource(
                //여기 draw scope 에서 dragging 중일 때 UI 를 구성 가능하다.
                drawDragDecoration = {
                    drawRect(color = Color.Red)
                }
            ){
                detectTapGestures(
                    onPress = {
                        startTransfer(
                            DragAndDropTransferData(
                                ClipData.newPlainText("Label", dragSource.title)
                            )
                        )
                    }
                )
            }
    ) {
        Text(dragSource.title)
        Spacer(Modifier.height(10.dp))
    }
}