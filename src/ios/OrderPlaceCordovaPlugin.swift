import OrderPlaceSdk
import UIKit

@objc(OrderPlaceCordovaPlugin) class OrderPlaceCordovaPlugin : CDVPlugin {
    
    func openUrl(_ command: CDVInvokedUrlCommand) {
       
        let url = command.argument(at: 0) as! String
        let features = command.argument(at: 1) as! NSArray
        
        let result: [String: String] = [:]
            
        let pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAs: result
        )
        
        let featureString = features.componentsJoined(by: ",")
        
        print("openUrl", url, featureString)
        
        OrderPlace.openUrl(caller: self.viewController, url: url, features: featureString)
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    
    func scan(_ command: CDVInvokedUrlCommand) {
        
        let features = command.argument(at: 0) as! NSArray
        
        let result: [String: String] = [:]
        
        let pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAs: result
        )
        
        let featureString = features.componentsJoined(by: ",")
        
        print("scan", featureString)
        
        OrderPlace.scan(caller: self.viewController, features: featureString)
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
}
