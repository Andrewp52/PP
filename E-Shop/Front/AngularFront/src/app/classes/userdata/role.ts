import {AbstractEntity} from "../abstract-entity";
import {User} from "./user";

export class Role extends AbstractEntity{
  role: string | undefined
  description: string | undefined
  users: User[] | undefined
}
