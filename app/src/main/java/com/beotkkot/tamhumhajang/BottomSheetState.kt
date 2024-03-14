package com.beotkkot.tamhumhajang

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

typealias SheetContent = @Composable ColumnScope.() -> Unit

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberWineyBottomSheetState(
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    scope: CoroutineScope = rememberCoroutineScope(),
): BottomSheetState {

    val bottomSheetContent: MutableState<SheetContent?> = remember {
        mutableStateOf(null)
    }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = {
            true
        },
        skipHalfExpanded = true
    )

    return remember(bottomSheetState, bottomSheetContent) {
        BottomSheetState(
            bottomSheetState = bottomSheetState,
            bottomSheetContent = bottomSheetContent,
            keyboardController = keyboardController,
            scope = scope
        )
    }
}

@Stable
class BottomSheetState @OptIn(ExperimentalMaterialApi::class) constructor(
    val bottomSheetContent: MutableState<SheetContent?>,
    val bottomSheetState: ModalBottomSheetState,
    val keyboardController: SoftwareKeyboardController?,
    val scope: CoroutineScope
) {

    val setBottomSheet: (SheetContent) -> Unit = { content: SheetContent ->
        bottomSheetContent.value = content
    }
    val clearBottomSheet: () -> Unit = {
        bottomSheetContent.value = null
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun showBottomSheet(content: SheetContent) = scope.launch {
        keyboardController?.hide()
        bottomSheetState.hide()
        setBottomSheet(content)
        bottomSheetState.show()
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun hideBottomSheet() = scope.launch {
        keyboardController?.hide()
        clearBottomSheet()
        bottomSheetState.hide()
    }
}