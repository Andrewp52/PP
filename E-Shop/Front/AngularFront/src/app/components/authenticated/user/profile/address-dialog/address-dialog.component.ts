import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Address} from "../../../../../classes/userdata/address";

@Component({
  selector: 'app-address-dialog',
  templateUrl: './address-dialog.component.html',
  styleUrls: ['./address-dialog.component.css']
})
export class AddressDialogComponent {
  myGroup: FormGroup = new FormGroup<any>({});
  constructor(@Inject(MAT_DIALOG_DATA) public data: Address, public dialogRef: MatDialogRef<AddressDialogComponent>) {
    this.myGroup.addControl('id', new FormControl(this.data?.id))
    this.myGroup.addControl('city', new FormControl(this.data?.city, Validators.required));
    this.myGroup.addControl('street', new FormControl(this.data?.street, Validators.required));
    this.myGroup.addControl('building', new FormControl(this.data?.building, Validators.required));
    this.myGroup.addControl('aptOffice', new FormControl(this.data?.aptOffice));
  }

  proceed() {
    this.dialogRef.close(this.myGroup?.value as Address);
  }
}
