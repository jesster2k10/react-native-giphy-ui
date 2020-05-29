//
//  GiphyCache.swift
//  GiphyUi
//
//  Created by Jesse Onolememen on 27/03/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
import GiphyCoreSDK
import GiphyUISDK

@objc(RNGiphyCache)
class RNGiphyCache: NSObject {
  @objc func setDiskCacheByteLimit(_ limit: Int) {
    GPHCache.shared.diskCacheByteLimit = UInt(limit)
  }
  
  @objc func setCacheSetting(_ setting: String) {
    switch setting {
    case "memoryOnly":
      GPHCache.shared.setting = .memoryOnly
    case "diskOnly":
      GPHCache.shared.setting = .diskOnly
    default:
      break
    }
  }
  
  @objc func clearCache() {
    GPHCache.shared.clear()
  }
  
  @objc func downalodAssetData(_ url: String, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) {
    GPHCache.shared.downloadAsset(url) { (image, error) in
      if let image = image, let data = image.pngData() {
        resolve([
          "data": data.base64EncodedString(),
          "width": image.size.width,
          "height": image.size.height,
        ])
      } else {
        reject("GiphyCacheDownloadAssetError", error?.localizedDescription, error)
      }
    }
  }
}
