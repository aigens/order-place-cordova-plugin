var exec = require('cordova/exec'),
    cordova = require('cordova');

exports.openUrl = function (success, error, params) {
    cordova.exec(success, error, 'order-place-cordova-plugin', 'openUrl', [params]);
};

exports.scan = function (success, error, params) {
    cordova.exec(success, error, 'order-place-cordova-plugin', 'scan', [params]);
};

exports.scanDecode = function (success, error, params) {
    cordova.exec(success, error, 'order-place-cordova-plugin', 'scanDecode', [params]);
};