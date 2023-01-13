import {ToggleableEntity} from "../toggleable-entity";
import {AbstractEntity} from "../abstract-entity";
import {Role} from "./role";

export class User extends ToggleableEntity{
  firstName: string | undefined
  lastName: string | undefined
  userName: string | undefined
  roles: AbstractEntity[] | undefined
  phones: AbstractEntity[] | undefined
  addresses: AbstractEntity[] | undefined

  public hasRole(name: string) : boolean {
    let r: Role[] = Object.assign([], this.roles);
    return r.find(role => role.role === name) != undefined;
  }
}
