import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {PasswordChangeRequest} from "../../../../../interfaces/password-change-request";


@Component({
  selector: 'app-change-password-dialog',
  templateUrl: './change-password-dialog.component.html',
  styleUrls: ['./change-password-dialog.component.css']
})
export class ChangePasswordDialogComponent {
  myGroup: FormGroup = new FormGroup({
    oldPassword: new FormControl('', Validators.required),
    newPassword: new FormControl('', [Validators.required, Validators.min(3)]),
    confirmPassword: new FormControl('', [Validators.required, Validators.min(3)])
  });


  constructor(public dialogRef: MatDialogRef<ChangePasswordDialogComponent>) {
  }

  proceed(){
    let res: PasswordChangeRequest = {
      oldPassword: this.myGroup.get('oldPassword')?.value,
      newPassword: this.myGroup.get('newPassword')?.value
    }
    this.dialogRef.close(res);
  }
}
