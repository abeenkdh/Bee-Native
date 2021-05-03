package com.bstudio.beenative

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.makeramen.roundedimageview.RoundedImageView

class BeeNative {
    companion object {
        private var nativeAds: NativeAd? = null

        @SuppressLint("MissingPermission")
        fun loadNativeAds(context: Context, nativeId: String, block: (NativeAd) -> Unit){
            MobileAds.initialize(context)
            val nativeLoader = AdLoader.Builder(context, nativeId)
                .forNativeAd { nativeAd ->
                    nativeAds = nativeAd
                    block.invoke(nativeAd)
                }.build()
            nativeLoader.loadAd(AdRequest.Builder().build())
        }

        @SuppressLint("InflateParams")
        fun displayNativeAd(parent: ViewGroup) {
            val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val adView = inflater.inflate(R.layout.native_view, null) as NativeAdView

            val headlineView = adView.findViewById<TextView>(R.id.ad_headline)
            val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
            val bodyView = adView.findViewById<TextView>(R.id.ad_body)
            val callToActionView = adView.findViewById<Button>(R.id.ad_call_to_action)
            val iconView = adView.findViewById<RoundedImageView>(R.id.ad_app_icon)
            val priceView = adView.findViewById<TextView>(R.id.ad_price)
            val starRatingView = adView.findViewById<AppCompatRatingBar>(R.id.ad_stars)
            val storeView = adView.findViewById<TextView>(R.id.ad_store)
            val advertiserView = adView.findViewById<TextView>(R.id.ad_advertiser)

            headlineView.text = nativeAds?.headline
            adView.headlineView = headlineView

            mediaView.setMediaContent(nativeAds?.mediaContent!!)
            adView.mediaView = mediaView

            bodyView.text = nativeAds?.body
            adView.bodyView = bodyView

            callToActionView.text = nativeAds?.callToAction
            adView.callToActionView = callToActionView

            iconView.setImageDrawable(nativeAds?.icon!!.drawable)
            adView.iconView = iconView

            priceView.text = nativeAds?.price
            adView.priceView = priceView

            starRatingView.rating = nativeAds?.starRating!!.toFloat()
            adView.starRatingView = starRatingView

            storeView.text = nativeAds?.store
            adView.storeView = storeView

            advertiserView.text = nativeAds?.advertiser
            adView.advertiserView = advertiserView

            adView.setNativeAd(nativeAds!!)

            parent.removeAllViews()
            parent.addView(adView)
        }
    }
}
