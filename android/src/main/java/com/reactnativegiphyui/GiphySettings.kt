package com.reactnativegiphyui

import com.facebook.react.bridge.ReadableMap
import com.giphy.sdk.core.models.enums.MediaType
import com.giphy.sdk.core.models.enums.RatingType
import com.giphy.sdk.ui.GPHContentType
import com.giphy.sdk.ui.GPHSettings
import com.giphy.sdk.ui.themes.GPHTheme
import com.giphy.sdk.ui.themes.GridType

class GiphySettings(private val config: ReadableMap) {
  fun settings(): GPHSettings {
    val settings = GPHSettings()
    settings.theme = theme()
    settings.gridType = layout()
    settings.rating = rating()
    settings.showConfirmationScreen = if (config.hasKey("showConfirmationScreen")) config.getBoolean("showConfirmationScreen") else false
    settings.mediaTypeConfig = mediaTypes()
    return settings
  }

  private fun theme(): GPHTheme {
    val theme = if (config.hasKey("theme")) config.getString("theme") else "automatic"
    return when (theme) {
      "dark" -> GPHTheme.Dark
      "light" -> GPHTheme.Light
      else -> GPHTheme.Automatic
    }
  }

  private fun layout(): GridType {
    val layout = if (config.hasKey("layout")) config.getString("layout") else "waterfall"
    return when (layout) {
      "waterfall" -> GridType.waterfall
      "carousel" -> GridType.carousel
      "emoji" -> GridType.emoji
      else -> GridType.waterfall
    }
  }

  private fun rating(): RatingType {
    val rating = if (config.hasKey("rating")) config.getString("rating") else "ratedPG13"
    return when (rating) {
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
    val mediaTypes = if (config.hasKey("mediaTypes")) config.getArray("mediaTypes")?.toArrayList() else null
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
