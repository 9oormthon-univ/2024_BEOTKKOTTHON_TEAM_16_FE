package com.beotkkot.tamhumhajang.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beotkkot.tamhumhajang.AppState
import com.beotkkot.tamhumhajang.BottomSheetState
import com.beotkkot.tamhumhajang.data.model.response.Badge
import com.beotkkot.tamhumhajang.data.model.response.Reward
import com.beotkkot.tamhumhajang.design.component.TopBar
import com.beotkkot.tamhumhajang.design.theme.TamhumhajangTheme
import com.beotkkot.tamhumhajang.ui.popup.CouponPopup

@Composable
fun ProfileScreen(
    appState: AppState,
    bottomSheetState: BottomSheetState,
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val effectFlow = viewModel.effect

    LaunchedEffect(true) {
        viewModel.getProfile()

        effectFlow.collect {
            when (it) {
                is ProfileContract.Effect.NavigateTo -> {
                    appState.navigate(it.destination)
                }
                is ProfileContract.Effect.ShowSnackBar -> {
                    appState.showSnackbar(it.message)
                }
            }
        }
    }

    if (uiState.showCouponPopup) {
        uiState.couponId?.let { rewardId ->
            CouponPopup(rewardId) {
                viewModel.useReward(rewardId)
                viewModel.showCouponPopup(false, null)
            }
        }
    }

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
                        append(uiState.nickname)
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
                if (uiState.previousImage != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(uiState.previousImage)
                            .build(),
                        contentDescription = "IMG_PREVIOUS_GRADE",
                        modifier = Modifier
                            .size(72.dp)
                            .background(
                                shape = CircleShape,
                                color = TamhumhajangTheme.colors.color_9ddb80
                            )
                            .padding(10.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Fit
                    )
                } else {
                    Spacer(modifier = Modifier.size(72.dp))
                }

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(uiState.currentImage)
                        .build(),
                    contentDescription = "IMG_CURRENT_GRADE",
                    modifier = Modifier
                        .size(122.dp)
                        .background(
                            shape = CircleShape,
                            color = TamhumhajangTheme.colors.color_9ddb80
                        )
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(uiState.nextImage)
                        .build(),
                    contentDescription = "IMG_NEXT_IMAGE",
                    modifier = Modifier
                        .size(72.dp)
                        .background(
                            shape = CircleShape,
                            color = TamhumhajangTheme.colors.color_9ddb80
                        )
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = uiState.characterName,
                style = TamhumhajangTheme.typography.title3.copy(
                    color = TamhumhajangTheme.colors.color_000000
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(7.dp))

            Text(
                text = uiState.gradeDescription,
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

            val rewardCount = uiState.bookRows.map { it.reward }.count { it.isAcquired }

            Text(
                modifier = Modifier.align(Alignment.End),
                text = buildAnnotatedString {
                    append("\uD83C\uDFC6 트로피 ")
                    withStyle(SpanStyle(color = TamhumhajangTheme.colors.color_0fa958)) {
                        append("$rewardCount")
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
                StampBoard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    badges = uiState.bookRows.map { it.badges }
                )

                RewardBoard(
                    rewards = uiState.bookRows.map { it.reward },
                    showCouponPopup = viewModel::showCouponPopup
                )
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
private fun StampBoard(
    modifier: Modifier = Modifier,
    badges: List<List<Badge>>
) {
    val context = LocalContext.current

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
        if (badges.size >= 3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                badges[0].let {
                    it.forEach { badge ->
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(badge.imgUrl)
                                .build(),
                            contentDescription = "IMG_BADGE",
                            modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color(0xFF999999))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                badges[1].let {
                    it.forEach { badge ->
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(badge.imgUrl)
                                .build(),
                            contentDescription = "IMG_BADGE",
                            modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color(0xFF999999))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                badges[2].let {
                    it.forEach { badge ->
                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(badge.imgUrl)
                                .build(),
                            contentDescription = "IMG_BADGE",
                            modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RewardBoard(
    modifier: Modifier = Modifier,
    rewards: List<Reward>,
    showCouponPopup: (Boolean, Int) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        rewards.forEach { reward ->
            RewardItem(reward, showCouponPopup)
        }
    }
}

@Composable
private fun RewardItem(
    reward: Reward,
    useReward: (Boolean, Int) -> Unit
) {
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
            )
            .clickable {
                if (reward.isAcquired && !reward.isUsed) useReward(true, reward.id)
            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(reward.imgUrl)
                .build(),
            contentDescription = "IMG_REWARD",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Crop
        )
    }
}