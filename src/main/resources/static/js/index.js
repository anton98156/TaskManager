function handleTaskBodyClick() {
    var taskBodies = document.querySelectorAll('.menu__task__body');
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
    var taskBodies = document.querySelectorAll('.menu__task__body');

    taskBodies.forEach(function(taskBody) {
        taskBody.querySelector('#updateButton').style.display = 'none';
        taskBody.querySelector('#deleteButton').style.display = 'none';
    });

    taskBodies.forEach(function(taskBody) {
        taskBody.addEventListener('mouseover', function() {
            this.querySelector('#updateButton').style.display = 'inline-block';
            this.querySelector('#deleteButton').style.display = 'inline-block';
        });

        taskBody.addEventListener('mouseout', function() {
            this.querySelector('#updateButton').style.display = 'none';
            this.querySelector('#deleteButton').style.display = 'none';
        });
    });
}


document.addEventListener('DOMContentLoaded', function() {
    addTaskButtonBehavior();
    handleTaskBodyClick();
});