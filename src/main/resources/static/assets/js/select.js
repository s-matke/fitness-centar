function selectAll() {
    var items = document.getElementsByName('checkboxes');
    console.log(items);
    for (var i = 0; i < items.length; i++) {
        if (items[i].type == 'checkbox')
            items[i].checked = true;
    }
}

function deselectAll() {
    var items = document.getElementsByName('checkboxes');
    for (let i = 0; i < items.length; i++) {
        if (items[i].type == 'checkbox')
            items[i].checked = false;
    }
}