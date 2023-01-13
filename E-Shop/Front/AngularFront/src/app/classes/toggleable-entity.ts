import {AbstractEntity} from "./abstract-entity";

export abstract class ToggleableEntity extends AbstractEntity{
  enabled: boolean | undefined
}
