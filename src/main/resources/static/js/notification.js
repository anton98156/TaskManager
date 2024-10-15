function handleTaskBodyClick() {
    var notificationBodies = document.querySelectorAll('.menu__record');
    notificationBodies.forEach(function(body) {
        body.addEventListener('click', function() {
            var notificationId = body.getAttribute('data-id');
            redirectToTaskOpenPage(notificationId);
        });
    });
}

function redirectToTaskOpenPage(notificationId) {
    window.location.href = '/task-open/' + notificationId;
}