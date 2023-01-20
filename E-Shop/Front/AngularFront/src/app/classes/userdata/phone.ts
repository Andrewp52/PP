import {AbstractEntity} from "../abstract-entity";

export class Phone extends AbstractEntity{
  phone: string | undefined
  users: AbstractEntity[] | undefined
}
