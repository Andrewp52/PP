import {Component, Inject, Injectable} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {DIALOG_DATA} from "@angular/cdk/dialog";
import {Address} from "../../../../../classes/userdata/address";
import {Phone} from "../../../../../classes/userdata/phone";

@Component({
  selector: 'app-phone-dialog',
  templateUrl: './phone-dialog.component.html',
  styleUrls: ['./phone-dialog.component.css']
})
export class PhoneDialogComponent {
  myGroup: FormGroup = new FormGroup<any>({});


  constructor(@Inject(MAT_DIALOG_DATA) public data: Phone, public dialogRef: MatDialogRef<PhoneDialogComponent>) {
    this.myGroup.addControl('id', new FormControl(this.data?.id))
    this.myGroup.addControl('phone', new FormControl(this.data?.phone, Validators.required));
  }

  proceed() {
    this.dialogRef.close(this.myGroup.value as Phone)
  }
}
