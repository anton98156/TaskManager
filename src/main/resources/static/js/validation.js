document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form')
    
    form.addEventListener('submit', function(event) {
        const name = document.getElementById("name").value
        const plannedEndDateTime = new Date(document.getElementById("plannedEndDateTime").value)
        const currentDateTime = new Date()

        console.log("Запланированная дата:", plannedEndDateTime);
        console.log("Текущая дата:", currentDateTime);

        if (name.trim() === "") {
            alert("Пожалуйста, введите название задачи!")
            event.preventDefault() // Блокирование формы.
            return false
        }

        if (name.length > 60) {
            alert("Название задачи слишком длинное!")
            event.preventDefault() // Блокирование формы.
            return false
        }

        if (plannedEndDateTime < currentDateTime) {
            alert("Планируемое завершение задачи не может быть в прошедшем времени!")
            event.preventDefault() // Блокирование формы.
            return false
        }

        return true // Отправление формы.
    })
})