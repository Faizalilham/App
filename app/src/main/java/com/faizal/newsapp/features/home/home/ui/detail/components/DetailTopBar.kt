package com.faizal.newsapp.features.home.home.ui.detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.faizal.newsapp.R
import com.faizal.newsapp.ui.theme.Dimens.MediumPadding1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookMarkClick: () -> Unit,
    onBackClick: () -> Unit,
    isFavorite: Boolean = false
) {

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body),
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {

            IconButton(onClick = onBookMarkClick) {
                if(isFavorite){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark_filled),
                        contentDescription = null
                    )
                }else{
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = null
                    )
                }
            }
            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
            }
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    modifier = Modifier.size(MediumPadding1),
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null
                )
            }
        },
    )
}