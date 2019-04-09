
import UIKit
import OrderPlaceSDK


@objc(OrderPlaceCordovaPlugin) class OrderPlaceCordovaPlugin : CDVPlugin {
    var callbackId:String? = nil;
    
    @objc(openUrl:) func openUrl(_ command: CDVInvokedUrlCommand) {
       
        guard let params = command.argument(at: 0) as? [String: Any] else {return}
        self.callbackId = command.callbackId;
       
        let result: [String: String] = [:]
            
        let pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAs: result
        )
        pluginResult?.keepCallback = NSNumber(value: true);
        //let featureString = features.componentsJoined(by: ",")
        guard var url = params["url"] as? String else {return}
        
//        url = "http://192.168.0.237:8100/#/store/102945/mode/takeaway";
        
        print("openUrl", params)
        
        //OrderPlace.openUrl(caller: self.viewController, url: url, options: params)
        OrderPlace.openUrl(caller: self.viewController, url: url, options: params) { [weak self] (result) in
            print("openUrl result:\(result)")
            if let values = result as? [String: Any] {
                print("emitEvent:\(values)")
                var dict = [String: Any]();
                dict["closeData"] = values;
                self?.emitEvent(dict);
            }
        }
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
    
    private func emitEvent(_ event: [String: Any]) {
        if let cbID = self.callbackId {
            let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: event);
            pluginResult?.keepCallback = NSNumber(value: true);
            self.commandDelegate?.send(
                pluginResult,
                callbackId: cbID
            )
        }
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
