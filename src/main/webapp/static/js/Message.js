export default class Message {
    constructor(rawMessage) {
        const message = JSON.parse(rawMessage);
        message.data = JSON.parse(message.data);


        this.type = message.type;
        this.data = message.data;
    }
}
