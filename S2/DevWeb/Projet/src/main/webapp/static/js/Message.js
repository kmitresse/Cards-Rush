export default class Message {
    constructor(rawMessage) {
        // Parse the message
        const message = JSON.parse(rawMessage);
        message.data = JSON.parse(message.data);

        console.log(message);

        // Set the message properties
        this.type = message.type;
        this.data = message.data;
    }
}
