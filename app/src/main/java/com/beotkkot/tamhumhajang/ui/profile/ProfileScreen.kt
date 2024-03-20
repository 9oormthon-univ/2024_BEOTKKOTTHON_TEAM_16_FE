package com.beotkkot.tamhumhajang.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.R
import com.beotkkot.tamhumhajang.design.component.TopBar
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme

@Composable
fun ProfileScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState
) {
    val name = "용감한 탐험가"

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = TamhumhajangTheme.colors.color_ffffff
            )
    ) {
        TopBar("도감") {
            appState.navigateUp()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(
                        color = TamhumhajangTheme.colors.color_0fa958,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )) {
                        append(name)
                    }
                    append("님,\n모험을 시작해볼까요?")
                },
                style = TamhumhajangTheme.typography.title1
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("")
                        .build(),
                    contentDescription = "IMG_PREVIOUS_GRADE",
                    modifier = Modifier
                        .size(72.dp)
                        .background(
                            shape = CircleShape,
                            color = TamhumhajangTheme.colors.color_9ddb80
                        )
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("")
                        .build(),
                    contentDescription = "IMG_CURRENT_GRADE",
                    modifier = Modifier
                        .size(122.dp)
                        .background(
                            shape = CircleShape,
                            color = TamhumhajangTheme.colors.color_9ddb80
                        )
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("")
                        .build(),
                    contentDescription = "IMG_CURRENT_GRADE",
                    modifier = Modifier
                        .size(72.dp)
                        .background(
                            shape = CircleShape,
                            color = TamhumhajangTheme.colors.color_9ddb80
                        )
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "장터 탐험가",
                style = TamhumhajangTheme.typography.title3.copy(
                    color = TamhumhajangTheme.colors.color_000000
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = "조금만 더 화이팅 !\n3개만 더 모으면, 장터 보물사냥꾼이 될 수 있어요!",
                style = TamhumhajangTheme.typography.description3.copy(
                    color = TamhumhajangTheme.colors.color_000000
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color(0xFFD0D0D0))
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "배지 도감",
                style = TamhumhajangTheme.typography.title1.copy(
                    color = TamhumhajangTheme.colors.color_000000
                )
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                modifier = Modifier.align(Alignment.End),
                text = buildAnnotatedString {
                    append("\uD83C\uDFC6 트로피 ")
                    withStyle(SpanStyle(color = TamhumhajangTheme.colors.color_0fa958)) {
                        append("0")
                    }
                    append("개")
                },
                style = TamhumhajangTheme.typography.body1.copy(
                    color = Color(0xFF595959)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StampBoard(modifier = Modifier.fillMaxWidth().weight(1f))

                RewardBoard()
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
private fun StampBoard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(
                color = Color(0xFF6D7274),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 10.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color(0xFF999999)))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = Color(0xFF999999)))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}

@Composable
private fun RewardBoard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        RewardItem()
        RewardItem()
        RewardItem()
    }
}

@Composable
private fun RewardItem() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(16.dp),
                color = Color.Black
            ),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(R.drawable.ic_reward_disabled)
                .build(),
            contentDescription = "IMG_REWARD",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop
        )
    }
}