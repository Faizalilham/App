package com.faizal.newsapp.features.home.home.ui.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faizal.newsapp.UIKit.shimmerEffect
import com.faizal.newsapp.ui.theme.Dimens
import com.faizal.newsapp.ui.theme.Dimens.ArticleCardSize
import com.faizal.newsapp.ui.theme.Dimens.MediumPadding1
import com.faizal.newsapp.ui.theme.NewsAppTheme

@Composable
fun ArticleCardShimmer(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
                .padding(horizontal = Dimens.ExtraSmallPadding)
                .height(ArticleCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .padding(horizontal = MediumPadding1)
                    .size(ArticleCardSize)
                    .clip(MaterialTheme.shapes.medium)
                    .shimmerEffect()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(15.dp)
                        .padding(horizontal = MediumPadding1)
                        .size(ArticleCardSize)
                        .clip(MaterialTheme.shapes.medium)
                        .shimmerEffect()
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleCardShimmerPreview() {
    NewsAppTheme {
        ArticleCardShimmer()
    }
}