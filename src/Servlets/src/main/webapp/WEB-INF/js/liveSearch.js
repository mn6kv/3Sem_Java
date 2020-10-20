function sendUser(name, password) {

    let data = {
        "name" : name,
    };

    $.ajax({
        type: "POST",
        url: "/search",
        data: JSON.stringify(data),
        success: function (response) {
            renderTable(response, $('#table'))
        },
        dataType: "json",
        contentType: "application/json"
    })

}

function renderTable(users, table) {
    let innerHtml = '<tr>\n' +
        '               <th>FirstName</th>' +
        '               <th>LastName</th>' +
        '            </tr>';

    for (let i = 0; i < users.length; i++) {
        innerHtml += '<tr>';
        innerHtml += '  <td>' + users[i]['firstName'] + '</td>';
        innerHtml += '  <td>' + users[i]['lastName'] +  '</td>';
        innerHtml += '</tr>';
    }

    table.html(innerHtml);
}

