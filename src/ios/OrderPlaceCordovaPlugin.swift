
import UIKit
import OrderPlaceSDK


@objc(OrderPlaceCordovaPlugin) class OrderPlaceCordovaPlugin : CDVPlugin {
    
    @objc(openUrl:) func openUrl(_ command: CDVInvokedUrlCommand) {
       
        guard let params = command.argument(at: 0) as? [String: Any] else {return}
       
        let result: [String: String] = [:]
            
        let pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAs: result
        )
        
        //let featureString = features.componentsJoined(by: ",")
        guard let url = params["url"] as? String else {return}
        
        print("openUrl", params)
        
        OrderPlace.openUrl(caller: self.viewController, url: url, options: params)
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    
    @objc(scan:) func scan(_ command: CDVInvokedUrlCommand) {
        
        let params = command.argument(at: 0) as! [String: Any]
        
        let result: [String: String] = [:]
        
        let pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAs: result
        )
        
        print("scan", params)
        
        OrderPlace.scan(caller: self.viewController, options: params)
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
}
