importScripts('https://www.gstatic.com/firebasejs/5.0.4/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/5.0.4/firebase-messaging.js');

var config = {
	apiKey: "AIzaSyCGQntVBcg1RdZdOnM-bVvbJ9cCajCcwYk",
	authDomain: "sidcfcmtest.firebaseapp.com",
	databaseURL: "https://sidcfcmtest.firebaseio.com",
	projectId: "sidcfcmtest",
	storageBucket: "sidcfcmtest.appspot.com",
	messagingSenderId: "432292274138"
};
firebase.initializeApp(config);

const messaging = firebase.messaging();

// messaging.setBackgroundMessageHandler(function(payload) {
// 	console.log('[firebase-messaging-sw.js] ', payload);
// 	// Customize notification here
// 	var notificationTitle = 'Background Message Title';
// 	var notificationOptions = {
// 		body: 'Background Message body.',
// 		icon: '/firebase-logo.png'
// 	};
	
// 	return self.registration.showNotification(notificationTitle,notificationOptions);
// });