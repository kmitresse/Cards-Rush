<%@ tag import="com.google.gson.Gson" %>
<%@ tag import="uppa.project.database.pojo.User" %>
<%@tag description="component/connectedUserList" pageEncoding="UTF-8" %>
<%@taglib prefix="component" tagdir="/WEB-INF/tags/components" %>

<component:card>
    <jsp:attribute name="card_head">
        <div class="card-header-title">Utilisateurs connectés</div>
    </jsp:attribute>
    <jsp:attribute name="card_content">
        <table id="connectedUserList" class="table is-fullwidth">
            <thead>
                <tr>
                    <th>Utilisateur</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </jsp:attribute>
</component:card>

<script defer type="module">
    const tbodyElement = document.querySelector('#connectedUserList tbody');

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
    websocket.onerror = (error) => {}

    function updateUserList(users) {
        tbodyElement.innerHTML = '';
        users.forEach(user => {
            const trElement = document.createElement('tr');
            const tdElement = [
                document.createElement('td'),
                document.createElement('td')
            ];
            const buttonElement = document.createElement('button');

            tdElement[0].textContent = user.username;

            // If it's not the current user, we can display the button
            if (user.id !== <%= user.getId() %>) {
                buttonElement.classList.add('button', 'is-light');
                buttonElement.textContent = 'Inviter';

                // TODO Ajouter l'événement click
            }

            tdElement[1].appendChild(buttonElement);
            trElement.appendChild(tdElement[0]);
            trElement.appendChild(tdElement[1]);

            tbodyElement.appendChild(trElement);
        });
    }
</script>