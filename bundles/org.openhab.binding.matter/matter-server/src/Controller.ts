import { WebSocketSession } from "./app";
import { Request } from "./MessageTypes";

export abstract class Controller {

    constructor(protected ws: WebSocketSession) {
    }
    
    abstract handleRequest(request: Request): Promise<void>;
    abstract close(): void;
}