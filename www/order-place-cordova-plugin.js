var exec = require('cordova/exec');

exports.openUrl = function (success, error, args) {
    exec(success, error, 'order-place-cordova-plugin', 'openUrl', args);
};

exports.scan = function (success, error, args) {
    exec(success, error, 'order-place-cordova-plugin', 'scan', args);
};

window['OrderPlace'] = cordova.plugins.OrderPlace;