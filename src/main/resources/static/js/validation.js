document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector('form')

    form.addEventListener('submit', function(event) {
        const name = document.getElementById("name").value
        const description = document.getElementById("description").value

        if (name.trim() === "" || description.trim() === "") {
            alert("Пожалуйста, заполните все поля!")
            event.preventDefault() // Блокирование формы.
            return false
        }

        return true // Отправление формы.
    })
})