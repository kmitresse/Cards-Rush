const session = JSON.parse(sessionStorage.getItem('session'));

url = new URL(window.location.href);
url.protocol = 'ws:';

const websocket = new WebSocket(url);

websocket.onopen = () => {
    const linkUserSession = {
        type: 'linkUserSession',
        data: sessionStorage.getItem('user')
    }
    websocket.send(JSON.stringify(linkUserSession));
}

websocket.onmessage = (event) => {
    const data = JSON.parse(event.data);

    if (data.type === 'userList') {
        console.log(JSON.parse(data.data));
    }
}

websocket.onclose = () => {}
websocket.onerror = (error) => {}