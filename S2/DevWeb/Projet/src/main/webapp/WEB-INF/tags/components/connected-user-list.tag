<%@tag description="component/connected-user-list" pageEncoding="UTF-8" %>

<%@tag import="com.google.gson.Gson" %>
<%@tag import="uppa.project.database.pojo.User" %>

<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<table id="connected-user-list" class="table is-fullwidth">
    <thead>
    <tr>
        <th>Utilisateur</th>
<%--        <th>Nombre de parties</th>--%>
<%--        <th>Victoires (%)</th>--%>
<%--        <th>Clics corrects (%)</th>--%>
<%--        <th>Clics rapides (%)</th>--%>
<%--        <th>Action</th>--%>
    </tr>
    </thead>
    <tbody></tbody>
</table>

<script defer type="module">
    const tbodyElement = document.querySelector('#connected-user-list tbody');

    // effacer ce qu'il y a apres /project_war_exploded
    const url = new URL(window.location.href);
    url.pathname = "${pageContext.request.contextPath}/ws/connected-users";
    url.protocol = "ws:"

    const websocket = new WebSocket(url);

    <%
    Gson gson = new Gson();
    User user = (User) request.getSession().getAttribute("user");
    %>
    websocket.onopen = () => {
        const linkUserSession = {
            type: 'linkUserSession',
            data: JSON.stringify(<%= gson.toJson(user) %>)
        }
        websocket.send(JSON.stringify(linkUserSession));
    }

    websocket.onmessage = (event) => {
        const data = JSON.parse(event.data);

        if (data.type === 'userList') {
            const users = JSON.parse(data.data);
            updateUserList(users);
        }
    }

    websocket.onclose = () => {}
    websocket.onerror = (error) => console.error(error);

    function updateUserList(users) {
        tbodyElement.innerHTML = '';
        users.forEach(user => {
            const trElement = document.createElement('tr');
            const tdElement = [
                document.createElement('td'),
                // document.createElement('td'),
                // document.createElement('td'),
                // document.createElement('td'),
                // document.createElement('td'),
                // document.createElement('td')
            ];
            // const buttonElement = document.createElement('button');

            tdElement[0].textContent = user.username;
            // tdElement[1].textContent = user.nbPlayedGames;
            // tdElement[2].textContent = user.WinRate + '%';
            // tdElement[3].textContent = user.rightClickPercentRate + '%';
            // tdElement[4].textContent = user.rapidClickPercentRate + '%';

            // If it's not the current user, we can display the button
            <%--if (user.id !== <%= user.getId() %>) {--%>
            <%--    buttonElement.classList.add('button', 'is-light');--%>
            <%--    buttonElement.textContent = 'Inviter';--%>

            <%--    // TODO Ajouter l'événement click--%>
            <%--}--%>

            // tdElement[5].appendChild(buttonElement);
            trElement.appendChild(tdElement[0]);
            // trElement.appendChild(tdElement[1]);
            // trElement.appendChild(tdElement[2]);
            // trElement.appendChild(tdElement[3]);
            // trElement.appendChild(tdElement[4]);
            // trElement.appendChild(tdElement[5]);

            tbodyElement.appendChild(trElement);
        });
    }
</script>
