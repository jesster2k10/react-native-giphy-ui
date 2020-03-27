//
//  GiphyEnums.swift
//  GiphyUi
//
//  Created by Jesse Onolememen on 02/10/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

import Foundation
import GiphyUISDK
import GiphyCoreSDK

protocol GiphyEnum {
  associatedtype Enum
  
  var actualValue: Enum { get }
}

enum GiphyTheme: String, GiphyEnum {
  typealias Enum = GPHTheme
  
  case dark
  case light
  
  var actualValue: GPHTheme {
    switch self.rawValue {
    case "dark":
      return .dark
    case "light":
      return .light
    default:
      return .dark
    }
  }
}

enum GiphyLayout: String, GiphyEnum {
  typealias Enum = GPHGridLayout
  
  case waterfall
  case carousel
  
  var actualValue: GPHGridLayout {
    switch self.rawValue {
    case "waterfall":
      return .waterfall
    case "carousel":
      return .carousel
    default:
      return .waterfall
    }
  }
}

enum GiphyRating: String, GiphyEnum {
  typealias Enum = GPHRatingType
  
  case nsfw
  case ratedG
  case ratedR
  case ratedY
  case unrated
  case ratedPG13
  case ratedPG
  
  var actualValue: GPHRatingType {
    switch self.rawValue {
    case "nsfw":
      return .nsfw
    case "ratedG":
      return .ratedG
    case "ratedR":
      return .ratedR
    case "ratedY":
      return .ratedY
    case "unrated":
      return .unrated
    case "ratedPG":
      return .ratedPG
    case "ratedPG13":
      return .ratedPG13
    default:
      return .ratedPG13
    }
  }
}

enum GiphyMediaType: String, GiphyEnum {
  typealias Enum = GPHContentType
  
  case gifs
  case stickers
  case text
  case emoji
  
  var actualValue: GPHContentType {
    switch self.rawValue {
    case "gifs":
      return .gifs
    case "stickers":
      return .stickers
    case "text":
      return .text
    case "emoji":
      return .emoji
    default:
      return .gifs
    }
  }
}
