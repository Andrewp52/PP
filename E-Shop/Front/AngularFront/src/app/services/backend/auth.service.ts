import { Injectable } from '@angular/core';
import { Globals } from "../../../globals";
import {LoginRequest} from "../../interfaces/login-request";
import {Observable} from "rxjs";
import {SignupRequest} from "../../interfaces/signup-request";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {StorageService} from "../local/storage.service";
import {User} from "../../classes/userdata/user";
import {Role} from "../../classes/userdata/role";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  constructor(private httpClient: HttpClient, private storageService: StorageService) { }

  public login(request: LoginRequest) : Observable<any> {
    return  new Observable((observer) => {
      this.sendRequest(Globals.LOGIN, request).subscribe({
        next: value => {
          this.storageService.saveUser(value);
          observer.next(value);
        }, error: err => {
          observer.error(err.status);
        }
      })
    })
  }

  public logoff() : Observable<any>{
    return new Observable<any>((observer) => {
      this.sendRequest(Globals.LOGOFF, null).subscribe({
        next: value => {
          this.storageService.cleanUser();
          observer.next(value);
        }, error: err => {
          observer.error(err);
        }
      })
    })
  }

  public signup(request: SignupRequest) : Observable<any>{
    this.sendRequest(Globals.SIGNUP, request).subscribe({
      next: value => {

      }, error: err => {

      }
    });
    return new Observable<any>()
  }

  private sendRequest(part: string, request: any) : Observable<any>{
    return this.httpClient.post(Globals.API + part, request, httpOptions);
  }

  hasRole(name: string) {
    if(this.isLoggedIn()){
      let roles: Role[] | undefined = (this.storageService.getUser() as User).roles as Role[];
      if(roles){
        return roles?.find(role => role.role === name) != undefined;
      }
    }
    return false;
  }

  isLoggedIn() {
    return this.storageService.isLoggedIn();
  }
}
