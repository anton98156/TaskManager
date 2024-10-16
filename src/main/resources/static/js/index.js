function handleTaskBodyClick() {
    var taskBodies = document.querySelectorAll('.menu__record');
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
    var taskBodies = document.querySelectorAll('.menu__record');

    taskBodies.forEach(function(taskBody) {
        taskBody.querySelector('#button-move').style.display = 'none';
        taskBody.querySelector('#button-update').style.display = 'none';
        taskBody.querySelector('#button-delete').style.display = 'none';
    });

    taskBodies.forEach(function(taskBody) {
        taskBody.addEventListener('mouseover', function() {
            this.querySelector('#button-move').style.display = 'inline-block';
            this.querySelector('#button-update').style.display = 'inline-block';
            this.querySelector('#button-delete').style.display = 'inline-block';
        });

        taskBody.addEventListener('mouseout', function() {
            this.querySelector('#button-move').style.display = 'none';
            this.querySelector('#button-update').style.display = 'none';
            this.querySelector('#button-delete').style.display = 'none';
        });
    });
}

document.addEventListener('DOMContentLoaded', function() {
    addTaskButtonBehavior();
    handleTaskBodyClick();
});


document.addEventListener('DOMContentLoaded', function() {
    const currentLocation = window.location.pathname;
    const headerTitle = document.getElementById('header-title');

    if (currentLocation === '/important-tasks') {
        headerTitle.textContent = 'Важные задачи';
    } else if (currentLocation === '/urgent-tasks') {
        headerTitle.textContent = 'Срочные задачи';
    }
    else if (currentLocation === '/') {
        headerTitle.textContent = 'Менеджер задач';
    }
});