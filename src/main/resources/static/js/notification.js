function handleNotificationBodyClick() {
    var notificationBodies = document.querySelectorAll('.menu__record');
    notificationBodies.forEach(function(body) {
        body.addEventListener('click', function() {
            var notificationId = body.getAttribute('data-id');
            redirectToggleRead(notificationId);
        });
    });
}

function redirectToggleRead(notificationId) {
    fetch('/notification/toggle-read/' + notificationId, {
        method: 'POST'
    })
    .then(response => {
        if (response.redirected) {
            window.location.href = response.url;
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

document.addEventListener('DOMContentLoaded', function() {
    handleNotificationBodyClick();
});