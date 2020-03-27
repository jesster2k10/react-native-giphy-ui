//
//  GiphyMediaViewManager.m
//  GiphyUi
//
//  Created by Jesse Onolememen on 03/10/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>

// import RCTUIManager
#if __has_include(<React/RCTUIManager.h>)
#import <React/RCTUIManager.h>
#elif __has_include(“RCTUIManager.h”)
#import "RCTUIManager.h"
#else
#import "React/RCTUIManager.h"
#endif

@interface RCT_EXTERN_MODULE(GiphyMediaViewManager, RCTViewManager)

RCT_EXPORT_VIEW_PROPERTY(media, NSDictionary)

@end
