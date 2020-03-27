//
//  GiphyMediaViewManager.swift
//  GiphyUi
//
//  Created by Jesse Onolememen on 27/03/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
import GiphyUISDK
import GiphyCoreSDK

@objc(GiphyMediaViewManager)
class GiphyMediaViewManager: RCTViewManager {
  let mediaView = GPHMediaView()
  
  var media: [String: Any]? = [:] {
    didSet {
      if let media = media {
        do {
          let giphyMedia = try GPHMedia.mapData(media, options: [:])
          mediaView.media = giphyMedia
        } catch {
          print("Invalid media object passed to GiphyMedia")
        }
      }
    }
  }
  
  override func view() -> UIView! {
    return mediaView
  }
}

// MARK:- Setters
extension GiphyMediaViewManager {
  @objc func setMedia(_ media: [String: Any]?) {
    self.media = media
  }
}
