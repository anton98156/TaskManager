function handleTaskBodyClick() {
    var deleteButtons = document.querySelectorAll('#button-delete');
    deleteButtons.forEach(function(deleteButton) {
        // Получение taskId из data-id кнопки.
        var taskId = deleteButton.getAttribute('data-id');

        deleteButton.addEventListener('click', function(event) {
            event.preventDefault(); // Предотвращение перехода по ссылке.
            event.stopPropagation();
            redirectDeleteTask(taskId);
        });
    });
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

// Инициализация обработчиков после загрузки страницы.
document.addEventListener('DOMContentLoaded', handleTaskBodyClick);