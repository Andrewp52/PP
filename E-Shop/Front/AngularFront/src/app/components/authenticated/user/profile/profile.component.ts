import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {User} from "../../../../classes/userdata/user";
import {UserService} from "../../../../services/backend/user.service";
import {MatDialog} from "@angular/material/dialog";
import {AddressDialogComponent} from "./address-dialog/address-dialog.component";
import {PhoneDialogComponent} from "./phone-dialog/phone-dialog.component";
import {DeleteConfirmDialogComponent} from "./delete-confirm-dialog/delete-confirm-dialog.component";
import {ChangePasswordDialogComponent} from "./change-password-dialog/change-password-dialog.component";
import {PasswordChangeRequest} from "../../../../interfaces/password-change-request";
import {MainDataDialogComponent} from "./main-data-dialog/main-data-dialog.component";
import {Phone} from "../../../../classes/userdata/phone";
import {PhoneUpdateRequest} from "../../../../interfaces/phone-update-request";
import {AbstractEntity} from "../../../../classes/abstract-entity";
import {Address} from "../../../../classes/userdata/address";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  constructor(private route: ActivatedRoute, private userService: UserService, public dialog: MatDialog) {

  }

  user?: User

  ngOnInit(): void {
    this.route.data.subscribe((r: any) => this.user = r.user);
  }


  editPhone(i: number) {
    let phone: Phone = this.user!.phones![i]
    this.dialog.open(PhoneDialogComponent, {
      data: phone
    }).afterClosed().subscribe({
      next: value => {
        if(value){
          this.userService.updatePhone(value).subscribe({
            next: value => this.user!.phones![i] = value
          });
        }
      }
    })
  }

  deletePhone(i: number) {
    let phone: Phone = this.user?.phones![i]!
    this.dialog.open(DeleteConfirmDialogComponent, {
      data: ' phone ' + phone.phone
    }).afterClosed().subscribe({
      next: value => {
        if(value){
          this.userService.deletePhone(phone!.id!).subscribe(v => {
            let p: Phone[] = Object.assign([], this.user?.phones!);
            this.user!.phones = p.filter(ph => ph.id !== phone.id!)
          })
        }
      }
    })
  }

  editAddress(i: number) {
    this.dialog.open(AddressDialogComponent, {
      data: this.user!.addresses![i]
    }).afterClosed().subscribe({
      next: value => {
        if(value){
          this.userService.updateAddress(value).subscribe(r => this.user!.addresses![i] = r)
        }
      }
    })
  }

  deleteAddress(i: number) {
    let addr: Address = this.user?.addresses![i]!
    this.dialog.open(DeleteConfirmDialogComponent, {
      data: ' address ' + addr.city + addr.street + addr.building + addr.aptOffice
    }).afterClosed().subscribe({
      next: value => {
        if(value){
          this.userService.deleteAddress(addr!.id!).subscribe(v => {
              let a: Address[] = Object.assign([], this.user?.addresses!);
              this.user!.addresses = a.filter(ad => ad.id !== addr.id!)
              location.reload()
            }
          )
        }
      }
    })
  }

  addNewAddress() {
    this.dialog.open(AddressDialogComponent, {
    }).afterClosed().subscribe({
      next: value => {
        if(value){
          this.userService.addNewAddress(value).subscribe(r => this.user?.addresses?.push(r));
        }
      }
    })
  }

  addNewPhone() {
    this.dialog.open(PhoneDialogComponent, {
    }).afterClosed().subscribe({
      next: value => {
        if(value){
          this.userService.addNewPhone(value).subscribe(r => this.user?.phones?.push(r));
        }
      }
    })
  }

  changePassword() {
    this.dialog.open(ChangePasswordDialogComponent, {
    }).afterClosed().subscribe({
      next: value => console.log(value)
    })
  }

  editMainData() {
    this.dialog.open(MainDataDialogComponent, {
      data: this.user
    }).afterClosed().subscribe({
      next: value => console.log(value)
    })
  }
}
