//
//  RNGiphyCache.m
//  GiphyUi
//
//  Created by Jesse Onolememen on 27/03/2020.
//  Copyright © 2020 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>

#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#elif __has_include(“RCTBridgeModule.h”)
#import "RCTBridgeModule.h"
#else
#import "React/RCTBridgeModule.h"
#endif

@interface RCT_EXTERN_MODULE(RNGiphyCache, NSObject)

RCT_EXTERN_METHOD(clear)
RCT_EXTERN_METHOD(setDiskCacheByteLimit: (int)limit)
RCT_EXTERN_METHOD(setSetting: (NSString *)setting)
RCT_EXTERN_METHOD(downalodAssetData: (NSString *)url resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock) reject)

@end
