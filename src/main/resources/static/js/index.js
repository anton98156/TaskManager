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

function addTaskButtonBehavior() {
    var taskBodies = document.querySelectorAll('.task-body');

    taskBodies.forEach(function(taskBody) {
        taskBody.querySelector('.update-button').style.display = 'none';
        taskBody.querySelector('.delete-button').style.display = 'none';
    });

    taskBodies.forEach(function(taskBody) {
        taskBody.addEventListener('mouseover', function() {
            this.querySelector('.update-button').style.display = 'inline-block';
            this.querySelector('.delete-button').style.display = 'inline-block';
        });

        taskBody.addEventListener('mouseout', function() {
            this.querySelector('.update-button').style.display = 'none';
            this.querySelector('.delete-button').style.display = 'none';
        });
    });
}


document.addEventListener('DOMContentLoaded', function() {
    addTaskButtonBehavior();
    handleTaskBodyClick();
});