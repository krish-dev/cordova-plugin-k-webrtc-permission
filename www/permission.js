/*
===========================================================================
 * Developed By     :   Krishnendu Sekhar Das
 * Company          :   Indus Net Technologies Pvt. Ltd.
 * Plugin Name      :   KwebRTCPermission
 * Licence			: 	MIT
===========================================================================
*/

function KwebRTCPermission() { }

KwebRTCPermission.prototype.check = function (successCallback, errorCallback) {
	cordova.exec(
		successCallback,
		errorCallback,
		"KwebRTCPermission",
		"check",
		[]
	);
};

KwebRTCPermission.prototype.request = function (successCallback, errorCallback) {
	cordova.exec(
		successCallback,
		errorCallback,
		"KwebRTCPermission",
		"request",
		[]
	);
};

KwebRTCPermission.install = function () {
	if (!window.plugins.k) {
		window.plugins.k = {};
	}
	window.plugins.k.webrtc.permission = new KwebRTCPermission();
	return window.plugins.k.webrtc.permission;
};

cordova.addConstructor(KwebRTCPermission.install);
