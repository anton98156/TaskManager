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
        taskBody.querySelector('.menu__task__body__updateButton').style.display = 'none';
        taskBody.querySelector('.menu__task__body__deleteButton').style.display = 'none';
    });

    taskBodies.forEach(function(taskBody) {
        taskBody.addEventListener('mouseover', function() {
            this.querySelector('.menu__task__body__updateButton').style.display = 'inline-block';
            this.querySelector('.menu__task__body__deleteButton').style.display = 'inline-block';
        });

        taskBody.addEventListener('mouseout', function() {
            this.querySelector('.menu__task__body__updateButton').style.display = 'none';
            this.querySelector('.menu__task__body__deleteButton').style.display = 'none';
        });
    });
}


document.addEventListener('DOMContentLoaded', function() {
    addTaskButtonBehavior();
    handleTaskBodyClick();
});