let googleLoginState = 'your_unique_state';

function openGoogleLoginPopup() {
    var width = 600;
    var height = 600;
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;

    sessionStorage.setItem('googleLoginState', googleLoginState);

    var popupWindow = window.open(
        "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/RoomBooking/Login&response_type=code&client_id=89306432316-rs2mce69g2ta88splene875bpvihem12.apps.googleusercontent.com&approval_prompt=force&state=" + googleLoginState,
        "GoogleLoginPopup",
        "width=" + width + ",height=" + height + ",left=" + left + ",top=" + top
    );

    if (window.focus) {
        popupWindow.focus();
    }
}

window.addEventListener('message', function (event) {
    if (event.data === 'googleLoginSuccess') {
        popupWindow.close();

        window.location.reload();
    }
});

window.opener.postMessage('googleLoginSuccess', '*');

window.close();

window.opener.location.reload();