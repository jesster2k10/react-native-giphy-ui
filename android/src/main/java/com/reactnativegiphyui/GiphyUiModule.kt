package com.reactnativegiphyui

import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.bridge.*
import com.giphy.sdk.core.models.Images
import com.giphy.sdk.core.models.Media
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.views.GiphyDialogFragment

class GiphyUiModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "RNGiphyUI"
    }

    @ReactMethod
    fun configure(apiKey: String) {
      Giphy.configure(reactApplicationContext, apiKey)
    }

    @ReactMethod
    fun present(config: ReadableMap, onSelect: Callback, onDismiss: Callback?) {
      val settings = GiphySettings(config).settings()
      val dialog = GiphyDialogFragment.newInstance(settings)
      dialog.gifSelectionListener = object: GiphyDialogFragment.GifSelectionListener {
        override fun onGifSelected(media: Media) {
          onSelect.invoke(serialize(media))
        }

        override fun onDismissed() {
          if (onDismiss !== null) {
            onDismiss()
          }
        }

        override fun didSearchTerm(term: String) {}
      }

      val compatActivity: AppCompatActivity = currentActivity as AppCompatActivity
      val fragmentManager = compatActivity.supportFragmentManager

      dialog.show(fragmentManager, "giphy_view")
    }

    private fun serialize(media: Media): ReadableMap {
      val map = Arguments.createMap()
      map.putString("id", media.id)
      map.putString("type", media.type.toString())
      map.putString("slug", media.slug)
      map.putString("url", media.url)
      map.putString("bitly_url", media.bitlyUrl)
      map.putString("bitly_gif_url", media.bitlyGifUrl)
      map.putString("content_url", media.contentUrl)
      map.putString("embed_url", media.embedUrl)
      map.putString("username", media.user?.username)
      map.putString("source", media.source)
      map.putString("rating", media.rating.toString())
      map.putString("source_tld", media.sourceTld)
      map.putString("update_datetime", media.updateDate.toString())
      map.putString("create_datetime", media.createDate.toString())
      map.putString("import_datetime", media.importDate.toString())
      map.putString("trending_datetime", media.trendingDate.toString())
      map.putString("title", media.title)
      map.putMap("images", serializeImages(media.images))
      return map
    }

    private fun serializeImages(images: Images): WritableMap {
      val map = Arguments.createMap()
      map.putMap("preview_gif", stringMap("url", images.preview?.gifUrl))
      map.putMap("preview", stringMap("mp4", images.preview?.mp4Url))
      map.putMap("looping", stringMap("url", images.looping?.gifUrl))
      map.putMap("original_still", stringMap("url", images.originalStill?.gifUrl))
      map.putMap("original", stringMap("url", images.original?.gifUrl))
      map.putMap("downsized", stringMap("url", images.downsized?.gifUrl))
      map.putMap("downsized_still", stringMap("url", images.downsizedStill?.gifUrl))
      map.putMap("downsized_small", stringMap("url", images.downsizedSmall?.gifUrl))
      map.putMap("downsized_medium", stringMap("url", images.downsizedMedium?.gifUrl))
      map.putMap("downsized_large", stringMap("url", images.downsizedLarge?.gifUrl))
      map.putMap("fixed_height_small_still", stringMap("url", images.fixedHeightSmallStill?.gifUrl))
      map.putMap("fixed_height_small", stringMap("url", images.fixedHeightSmall?.gifUrl))
      map.putMap("fixed_height_downsampled", stringMap("url", images.fixedHeightDownsampled?.gifUrl))
      map.putMap("fixed_height_still", stringMap("url", images.fixedHeightStill?.gifUrl))
      map.putMap("fixed_height", stringMap("url", images.fixedHeight?.gifUrl))
      map.putMap("fixed_width_small_still", stringMap("url", images.fixedWidthSmallStill?.gifUrl))
      map.putMap("fixed_width_downsampled", stringMap("url", images.fixedWidthDownsampled?.gifUrl))
      map.putMap("fixed_width_small", stringMap("url", images.fixedWidthSmall?.gifUrl))
      map.putMap("fixed_width_still", stringMap("url", images.fixedWidthStill?.gifUrl))
      map.putMap("fixed_width", stringMap("url", images.fixedWidth?.gifUrl))
      return map
    }

    private fun stringMap(key: String, value: String?): WritableMap {
      val map = Arguments.createMap()
      map.putString(key, value)
      return map
    }
}
