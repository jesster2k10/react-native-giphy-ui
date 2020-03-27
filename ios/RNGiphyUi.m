//
//  GiphyUI.m
//  Murch
//
//  Created by Jesse Onolememen on 02/10/2019.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNGiphyUI, NSObject)

RCT_EXTERN_METHOD(configure:(NSString *)apiKey)

RCT_EXTERN_METHOD(present:(NSDictionary *)options onSelect:(RCTResponseSenderBlock)onSelect onDismiss:(RCTResponseSenderBlock)onDismiss)

@end
