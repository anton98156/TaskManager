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
    window.location.href = '/notification/toggle-read/' + notificationId;
}

document.addEventListener('DOMContentLoaded', function() {
    handleNotificationBodyClick();
});