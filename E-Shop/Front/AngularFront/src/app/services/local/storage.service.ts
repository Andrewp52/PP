import { Injectable } from '@angular/core';
import {User} from "../../classes/userdata/user";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})

export class StorageService {

  constructor() { }

  isLoggedIn() {
    return !!window.sessionStorage.getItem(USER_KEY);
  }

  saveUser(user: User) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  cleanUser() {
    window.sessionStorage.removeItem(USER_KEY);
  }

  cleanSessionStorage(): void {
    window.sessionStorage.clear();
  }

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }
  }
}
