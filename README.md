# Cordova webRTC android permission
Cordova plugin to check and give required permission for android app.

Platform Support
----------------
* android

## Usage

How to add plugin
------------------------
Type following command from CLI to add this plugin

```
    cordova plugin add cordova-plugin-k-webrtc-permission
```

The plugin creates the object `window.plugins.k.webrtc.permission`.

Methods
------------------------
* check		:  	window.plugins.k.webrtc.permission.check(successCallback, errorCallback);

* request	:	window.plugins.k.webrtc.permission.request(successCallback, errorCallback);

Permission list
-------------------------------------------------------
* CAMERA

* WRITE_EXTERNAL_STORAGE

* RECORD_AUDIO

* MODIFY_AUDIO_SETTINGS


Result object of "check" & "request" method
-------------------------------------------------------
* **responseCode**	: Type Integer, The value should 0 for success.

* **response**		: Type Array, Its contains the object of each permission details


permission object

* **name**			: Type String, This is permission name. The value should be android.permission.CAMERA or WRITE_EXTERNAL_STORAGE or RECORD_AUDIO or MODIFY_AUDIO_SETTINGS

* **permission**		: Type Boolean, It may true or false


**Sample response object**
```
{
	"responseCode": 0, 
	"response": [
		{
			"name": "android.permission.CAMERA",
			"permission": true
		},
		{
			"name": "android.permission.WRITE_EXTERNAL_STORAGE",
			"permission": true
		},
		{
			"name": "android.permission.RECORD_AUDIO",
			"permission": true
		},
		{
			"name": "android.permission.MODIFY_AUDIO_SETTINGS",
			"permission": true
		}
	],
	"haveRequiredPermission": true
}
```

