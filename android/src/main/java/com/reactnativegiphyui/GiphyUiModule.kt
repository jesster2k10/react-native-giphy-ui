package com.reactnativegiphyui

import android.app.Activity
import androidx.core.content.ContextCompat
import android.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.bridge.*
import com.giphy.sdk.core.models.Media
import com.giphy.sdk.ui.GPHSettings
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.themes.GPHTheme
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
    fun show(config: ReadableMap, onSelect: Callback, onDismiss: Callback?) {
      val settings = GiphySettings(config).settings()
      val dialog = GiphyDialogFragment.newInstance(settings)
      dialog.gifSelectionListener = object: GiphyDialogFragment.GifSelectionListener {
        override fun onGifSelected(media: Media) {
          onSelect(media.userDictionary)
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
}
