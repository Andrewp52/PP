import {ToggleableEntity} from "../toggleable-entity";
import {AbstractEntity} from "../abstract-entity";
import {Role} from "./role";
import {Phone} from "./phone";
import {Address} from "./address";

export class User extends ToggleableEntity{
  firstName: string | undefined
  lastName: string | undefined
  userName: string | undefined
  roles: Role[] | undefined
  phones: Phone[] | undefined
  addresses: Address[] | undefined

  public hasRole(name: string) : boolean {
    let r: Role[] = Object.assign([], this.roles);
    return r.find(role => role.role === name) != undefined;
  }
}
