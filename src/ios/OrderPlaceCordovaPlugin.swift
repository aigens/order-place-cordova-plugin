@objc(OrderPlaceCordovaPlugin) class OrderPlaceCordovaPlugin : CDVPlugin {
    
    func openUrl(_ command: CDVInvokedUrlCommand) {
        
        /*
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_ERROR
        )
        */
        print("openUrl")
        
        var result: [String: String] = [:]
            
        var pluginResult = CDVPluginResult(
            status: CDVCommandStatus_OK,
            messageAs: result
        )
        
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
}
