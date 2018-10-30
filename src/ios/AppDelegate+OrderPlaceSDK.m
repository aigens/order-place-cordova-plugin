//
//  AppDelegate+OrderPlaceSDK.m
//  testSDKWithOC
//
//  Created by 陈培爵 on 2018/10/30.
//  Copyright © 2018年 PeiJueChen. All rights reserved.
//

#import "AppDelegate+OrderPlaceSDK.h"
#import <objc/runtime.h>
#import <OrderPlaceSDK/OrderPlaceSDK-Swift.h>
@implementation AppDelegate (OrderPlaceSDK)
+ (void)load {
    Method originalOpenURLWithSource = class_getClassMethod(self, @selector(application:openURL:sourceApplication:annotation:));
    Method swizzledOpenURLWithSource = class_getClassMethod(self, @selector(swizzled_application:openURL:sourceApplication:annotation:));
    method_exchangeImplementations(originalOpenURLWithSource, swizzledOpenURLWithSource);
    
    Method originalOpenURL = class_getClassMethod(self, @selector(application:openURL:options:));
    Method swizzledOpenURL = class_getClassMethod(self, @selector(swizzled_application:openURL:options:));
    method_exchangeImplementations(originalOpenURL, swizzledOpenURL);
    
}

/// NOTE: 9.0以后使用新API接口 , >= ios 9.0
- (BOOL)swizzled_application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey,id> *)options {
    [self swizzled_application:app openURL:url options:options];
    [OrderPlace application:app open:url];
    NSLog(@"swizzled_application openURL:%@",url);
    
    return YES;
}
/// <= ios 9.0
- (BOOL)swizzled_application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation {
    [self swizzled_application:application openURL:url sourceApplication:sourceApplication annotation:annotation];
    
    [OrderPlace application:application open:url];
    
    NSLog(@"swizzled_application openURL sourceApplication:%@",url);
    return YES;
}
@end
