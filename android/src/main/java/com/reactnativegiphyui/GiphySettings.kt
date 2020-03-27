package com.reactnativegiphyui

import com.facebook.react.bridge.ReadableMap
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.models.enums.RatingType
import com.giphy.sdk.ui.GPHContentType
import com.giphy.sdk.ui.GPHSettings
import com.giphy.sdk.ui.themes.GPHTheme
import com.giphy.sdk.ui.themes.GridType

class GiphySettings(val config: ReadableMap) {
  fun settings(): GPHSettings {
    val settings = GPHSettings()
    settings.theme = theme()
    settings.gridType = layout()
    settings.rating = rating()
    settings.showConfirmationScreen = config.getBoolean("showConfirmationScreen")
    settings.mediaTypeConfig = mediaTypes()
    return settings
  }

  private fun theme(): GPHTheme {
    return when (config.getString("theme")) {
      "dark" -> GPHTheme.Dark
      "light" -> GPHTheme.Light
      else -> GPHTheme.Automatic
    }
  }

  private fun layout(): GridType {
    return when (config.getString(("layout"))) {
      "waterfall" -> GridType.waterfall
      "carousel" -> GridType.carousel
      "emoji" -> GridType.emoji
      else -> GridType.waterfall
    }
  }

  private fun rating(): RatingType {
    return when (config.getString("rating")) {
      "ratedPG" -> RatingType.pg
      "ratedPG13" -> RatingType.pg13
      "ratedR" -> RatingType.r
      "nsfw" -> RatingType.nsfw
      "ratedY" -> RatingType.y
      "ratedG" -> RatingType.g
      "unrated" -> RatingType.unrated
      "ratedG" -> RatingType.g
      else -> RatingType.pg13
    }
  }

  private fun mediaTypes(): Array<GPHContentType> {
    val mediaTypes = config.getArray("mediaTypes")?.toArrayList()
    if (mediaTypes === null) {
      return emptyArray()
    }

    val mapped = mediaTypes.map { n ->
      when (n) {
        "gifs" -> GPHContentType.gif
        "emoji" -> GPHContentType.emoji
        "stickers" -> GPHContentType.sticker
        "text" -> GPHContentType.text
        else -> GPHContentType.gif
      }
    }

    return mapped.toTypedArray()
  }
}
