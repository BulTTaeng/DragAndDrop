package com.example.draganddropcompose.screen

import android.content.ClipDescription
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.draganddropcompose.model.TargetModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TargetScreen(modifier: Modifier) {
    val targetList: List<TargetModel> = listOf(
        TargetModel("first", "first subtitle"),
        TargetModel("second", "second subtitle"),
        TargetModel("third", "third subtitle")
    )

    FlowRow(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        targetList.forEachIndexed { index, targetModel ->
            TargetModelUi(targetModel) {
                targetList.getOrNull(index)?.insideStringList?.add(it)
            }
            Spacer(Modifier.width(10.dp))
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TargetModelUi(
    targetModel: TargetModel,
    updateTargetList: (String) -> Unit
) {
    var bgColor by remember { mutableStateOf(Color.Gray) }

    /*
        drop 과 관련된 event 를 처리하는 곳
        remember 의 key 값으로 targetModel 을 주지 않으면 targetList 가 변경되면 compose 의
        재활용 으로 인해 문제가 생기니 조심.
    */
    val target = remember(targetModel) {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val draggedData = event.toAndroidDragEvent()
                    .clipData.getItemAt(0).text
                updateTargetList(draggedData.toString())
                return true
            }

            override fun onEntered(event: DragAndDropEvent) {
                super.onEntered(event)
                bgColor = Color.Blue
            }

            override fun onEnded(event: DragAndDropEvent) {
                super.onEntered(event)
                bgColor = Color.Gray
            }

            override fun onExited(event: DragAndDropEvent) {
                super.onEntered(event)
                bgColor = Color.Gray
            }

        }
    }

    Column(
        modifier = Modifier
            .width(130.dp)
            .wrapContentHeight()
            .background(bgColor)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event.mimeTypes().contains(ClipDescription.MIMETYPE_TEXT_PLAIN)
                },
                target = target
            )
    ) {
        Text(targetModel.title)
        Spacer(Modifier.height(5.dp))
        Text(targetModel.subTitle)
        Spacer(Modifier.height(5.dp))
        targetModel.insideStringList.map {
            Text(it)
        }
    }
}