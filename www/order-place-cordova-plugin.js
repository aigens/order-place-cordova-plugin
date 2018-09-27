var exec = require('cordova/exec');

exports.openUrl = function (success, error, params) {
    exec(success, error, 'order-place-cordova-plugin', 'openUrl', [params]);
};

exports.scan = function (success, error, params) {
    exec(success, error, 'order-place-cordova-plugin', 'scan', [params]);
};
