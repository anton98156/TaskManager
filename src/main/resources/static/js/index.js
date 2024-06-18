function handleTaskBodyClick() {
    var taskBodies = document.querySelectorAll('.task-body');
    taskBodies.forEach(function(body) {
        body.addEventListener('click', function() {
            var taskId = body.getAttribute('data-id');
            redirectToTaskOpenPage(taskId);
        });
    });
}

function redirectToTaskOpenPage(taskId) {
    window.location.href = '/task-open/' + taskId;
}

document.addEventListener('DOMContentLoaded', function() {
    handleTaskBodyClick();
});