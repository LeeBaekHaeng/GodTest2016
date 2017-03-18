cordova.define("cordova-plugin-jailbreak-detection.JailbreakDetection", function(require, exports, module) {

var exec = require("cordova/exec");

var JailbreakDetection = function () {
    this.name = "JailbreakDetection";
};

JailbreakDetection.prototype.isJailbroken = function (successCallback, failureCallback) {
    exec(successCallback, failureCallback, "JailbreakDetection", "isJailbroken", []);
};

module.exports = new JailbreakDetection();

});
