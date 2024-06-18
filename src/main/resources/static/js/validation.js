document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form')

    form.addEventListener('submit', function(event) {
        const name = document.getElementById("name").value

        if (name.trim() === "") {
            alert("Пожалуйста, введите название задачи!")
            event.preventDefault() // Блокирование формы.
            return false
        }

        return true // Отправление формы.
    })
})