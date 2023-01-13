import {AbstractEntity} from "../abstract-entity";

export class Address {
  city: string | undefined
  street: string | undefined
  building: string | undefined
  aptOffice: string | undefined
  users: AbstractEntity[] | undefined
}
