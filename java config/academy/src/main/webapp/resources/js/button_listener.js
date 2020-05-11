window.addEventListener('load', () => {

    let elementsByClassName = document.getElementsByClassName("delete");

    if (elementsByClassName != null) {

        for (let i = 0; i < elementsByClassName.length; i++) {
            elementsByClassName[i].addEventListener('click', function (event) {
                if (confirm("The group may contain students!" +
                    " Are you sure you want to delete the group with the students?")) {

                    $(location).attr('href', this.value);
                }
            });
        }
    }
});