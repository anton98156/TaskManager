function handleTaskBodyClick() {
    var taskBodies = document.querySelectorAll('.menu__record');
    taskBodies.forEach(function(body) {
        var taskId = body.getAttribute('data-id');

        // Переход по клику на весь блок, кроме кнопок
        body.addEventListener('click', function(event) {
            if (!event.target.closest('.button')) {
                redirectToTaskOpenPage(taskId);
            }
        });

        // Обработчик для кнопки удаления
        var deleteButton = body.querySelector('#button-delete');
        deleteButton.addEventListener('click', function(event) {
            event.preventDefault(); // Предотвращает переход по ссылке
            event.stopPropagation(); // Предотвращает выполнение других обработчиков на этом элементе
            redirectDeleteTask(taskId);
        });
    });
}

function redirectToTaskOpenPage(taskId) {
    window.location.href = '/task-open/' + taskId;
}

function redirectDeleteTask(taskId) {
    fetch('/task-delete/' + taskId, {
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