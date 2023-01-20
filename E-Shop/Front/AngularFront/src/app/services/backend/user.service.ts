import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../../classes/userdata/user";
import {Phone} from "../../classes/userdata/phone";
import {Address} from "../../classes/userdata/address";
import {Globals} from "../../../globals";
import {PhoneUpdateRequest} from "../../interfaces/phone-update-request";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})

export class UserService {

  constructor(private client: HttpClient) { }

  public getProfile(): Observable<any>{
    return this.sendGet(this.getUrl(''));
  }

  public updateMainData(user: User): Observable<any>{
    return this.sendPut(this.getUrl(''), user);
  }

  public addNewPhone(phone: Phone): Observable<any>{
    return this.sendPost(this.getUrl(Globals.PHONE), phone);
  }

  public updatePhone(phone: Phone): Observable<any>{
    return this.sendPut(this.getUrl(Globals.PHONE), phone);
  }

  deletePhone(id: number) {
    return this.sendDelete(this.getUrl(Globals.PHONE + '/' + id));
  }

  public addNewAddress(addr: Address): Observable<any>{
    return this.sendPost(this.getUrl(Globals.ADDRESS), addr);
  }

  public updateAddress(addr: Address): Observable<any>{
    return this.sendPut(this.getUrl(Globals.ADDRESS), addr);
  }

  deleteAddress(id: number) {
    return this.sendDelete(this.getUrl(Globals.ADDRESS + '/' + id));
  }

  private sendGet(url: string): Observable<any>{
    return this.client.get(url);
  }

  private sendPost(url: string, request: any): Observable<any>{
    return this.client.post(url, request);
  }

  private sendPut(url: string, request: any): Observable<any>{
    console.log('Request to '+ url);
    return this.client.put(url, request, httpOptions);
  }

  private sendDelete(url: string){
    return this.client.delete(url, httpOptions);
  }

  private getUrl(part: string){
    return Globals.API + Globals.USERS + part;
  }



}
