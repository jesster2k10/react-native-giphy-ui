//
//  GiphyUi.swift
//  GiphyUi
//
//  Created by Jesse Onolememen on 27/03/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

import Foundation
import GiphyUISDK
import GiphyCoreSDK

@objc(RNGiphyUI)
class RNGiphyUI: RCTEventEmitter {
  
  private var onSelect: RCTResponseSenderBlock?
  private var onDismiss: RCTResponseSenderBlock?
  
  @objc func configure(_ apiKey: String) {
    GiphyUISDK.configure(apiKey: apiKey)
  }
  
  @objc func present(_ config: [String: Any], onSelect: @escaping RCTResponseSenderBlock, onDismiss: RCTResponseSenderBlock?) {
    DispatchQueue.main.sync {
      let giphy = GiphyViewController()
      self.setupGiphy(giphy, config: config)
      
      self.onSelect = onSelect
      self.onDismiss = onDismiss
      giphy.delegate = self
      
      UIApplication.shared.windows.first?.rootViewController?.present(giphy, animated: true, completion: nil)
    }
  }
  
  private func setupGiphy(_ giphy: GiphyViewController, config: [String: Any]) {
    let theme = (config["theme"] as? String) ?? "dark"
    let layout = (config["layout"] as? String) ?? "waterfall"
    let showConfirmationScreen = (config["showConfirmationScreen"] as? Bool) ?? true
    let shouldLocalizeSearch = config["shouldLocalizeSearch"] as? Bool ?? true
    let trayHeightMultipler = config["trayHeightMultipler"] as? CGFloat ?? 0.7
    let mediaTypes = config["mediaTypes"] as? [String] ?? ["gifs"]
    let rating = config["rating"] as? String ?? "ratedPG13"
    
    GiphyViewController.trayHeightMultiplier = trayHeightMultipler
    giphy.showConfirmationScreen = showConfirmationScreen
    giphy.shouldLocalizeSearch = shouldLocalizeSearch
    
    if let theme = GiphyTheme(rawValue: theme) {
      giphy.theme = theme.actualValue
    }
    
    if let layout = GiphyLayout(rawValue: layout) {
      giphy.layout = layout.actualValue
    }
    
    if let rating = GiphyRating(rawValue: rating) {
      giphy.rating = rating.actualValue
    }
    
    giphy.mediaTypeConfig = mediaTypes.map { (rawValue) -> GPHContentType in
      return GiphyMediaType(rawValue: rawValue)?.actualValue ?? .gifs
    }
  }
  
  @objc override func supportedEvents() -> [String]! {
    return ["GiphyMediaSelected", "GiphyDismissed"]
  }
  
  @objc override static func requiresMainQueueSetup() -> Bool {
    return true
  }
}

extension RNGiphyUI: GiphyDelegate {
  func didSelectMedia(giphyViewController: GiphyViewController, media: GPHMedia)   {
    giphyViewController.dismiss(animated: true)
    guard let json = media.jsonRepresentation else {
      return
    }
    
    onSelect?([json])
  }
  
  func didDismiss(controller: GiphyViewController?) {
    onDismiss?([[:]])
  }
}

