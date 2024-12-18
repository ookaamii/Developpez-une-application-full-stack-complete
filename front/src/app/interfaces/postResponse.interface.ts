import { User } from "./user.interface";
import { Topic } from "./topic.interface";

export interface PostResponse {
    id: number;
    title: string;
    author: string;
    content: string;
    user: User[];
    Topic: Topic[];
    date: Date;
}