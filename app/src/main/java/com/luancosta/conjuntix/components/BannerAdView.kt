package com.luancosta.conjuntix.components

import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.luancosta.conjuntix.R

@Composable
fun BannerAdView(isTest: Boolean = true) {
    val unitId = stringResource(id = if (isTest) R.string.ad_mob_banner_id_test else R.string.ad_mob_banner_id)
    AndroidView(modifier = Modifier.heightIn(min = 60.dp), factory = { context ->
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = unitId
            loadAd(AdRequest.Builder().build())
        }
    })
}