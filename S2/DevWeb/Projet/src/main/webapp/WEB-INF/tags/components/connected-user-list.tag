<jsp:useBean id="user" scope="session" type="uppa.project.database.pojo.User"/>
<%@tag description="component/connected-user-list" pageEncoding="UTF-8" %>

<%@tag import="com.google.gson.Gson" %>

<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<table id="connected-user-list" class="table is-fullwidth">
    <thead>
    <tr>
        <th>Utilisateur</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>

<script defer type="module">
    import WebsocketToolkit from "${pageContext.request.contextPath}/static/js/WebsocketToolkit.js";

    let users = [];
    function updateUsers() {
        const table = document.querySelector('#connected-user-list tbody');

        // Clear the table
        table.innerHTML = '';

        // Add the users to the table
        users.forEach(user => {
            const tr = document.createElement('tr');
            const td = document.createElement('td');

            td.dataset.id = user.id;
            td.textContent = user.username;

            tr.appendChild(td);
            table.appendChild(tr);
        });
    }

    // Create a new WebSocket
    const url = new URL(window.location.href);
    url.pathname = "${pageContext.request.contextPath}/ws/users/${user.id}";
    url.protocol = "ws:"

    const ws = new WebsocketToolkit(url);
    ws.onOpen(() => console.log("Connected to the server"));
    ws.onMessage("init", (data) => {
        users = data;
        updateUsers();
    });
    ws.onMessage("addUser", (data) => {
        users.push(data);
        updateUsers();
    });
    ws.onMessage("removeUser", (data) => {
        users = users.filter(user => user.id !== data.id);
        updateUsers();
    });
    ws.onMessage("invite", (data) => {
        const {from, to, game_id} = data;
        if (confirm(from.username + " vous a invité à rejoindre sa partie")) {
            window.location.href = "${pageContext.request.contextPath}/game?id=" + game_id;
        }
    })
    ws.onError((error) => console.error(error));
    ws.onClose(() => console.log("Disconnected from the server"));
</script>
