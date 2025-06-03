import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.OnBoardingPage
import com.example.borutoapp.presentation.screens.welcome.HorizontalPagerIndicator
import com.example.borutoapp.presentation.screens.welcome.WelcomeViewModel
import com.example.borutoapp.ui.theme.EXTRA_LARGE_PADDING
import com.example.borutoapp.ui.theme.PAGING_INDICATOR_SPACING
import com.example.borutoapp.ui.theme.PAGING_INDICATOR_WIDTH
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.activeIndicatorColor
import com.example.borutoapp.ui.theme.buttonBackgroundColor
import com.example.borutoapp.ui.theme.descriptionColor
import com.example.borutoapp.ui.theme.inactiveIndicatorColor
import com.example.borutoapp.ui.theme.titleColor
import com.example.borutoapp.ui.theme.welcomeScreenBackgroundColor
import com.example.borutoapp.utils.Constants.LAST_ON_BOARDING_PAGE
import com.example.borutoapp.utils.Constants.ON_BOARDING_PAGE_COUNT

@Composable
fun WelcomeScreen(
    onFinishButtonClick: () -> Unit,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )

    // new API: supply pageCount here
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { ON_BOARDING_PAGE_COUNT }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            pageCount = pagerState.pageCount,
            activeColor = activeIndicatorColor,
            inactiveColor = inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )

        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            onFinishButtonClick()
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxSize(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(id = R.string.on_boarding_image)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = onBoardingPage.title,
            color = titleColor,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(horizontal = SMALL_PADDING),
            text = onBoardingPage.description,
            color = descriptionColor,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Finish")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }

}

@Preview(showBackground = true)
@Composable
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}