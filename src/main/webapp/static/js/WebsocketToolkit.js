import Message from "./Message.js";

export default class WebsocketToolkit {

    action = {}

    constructor(url) {
        this.url = url;
        this.ws = new WebSocket(url);
    }

    onOpen(callback) {
        this.ws.onopen = callback;
    }

    onMessage(type, callback) {
        this.action[type] = callback;
        this.ws.onmessage = (event) => {
            const message = new Message(event.data);
            this.action[message.type](message.data)
        };
    }

    onClose(callback) {
        this.ws.onclose = callback;
    }

    onError(callback) {
        this.ws.onerror = callback;
    }
}