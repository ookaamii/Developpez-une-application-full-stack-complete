import { User } from "./user.interface";
import { Topic } from "./topic.interface";

export interface Subscription {
    id: number;
    topic: Topic;
    user: User;
}