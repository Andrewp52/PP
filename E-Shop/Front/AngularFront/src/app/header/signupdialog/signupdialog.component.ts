import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SignupRequest} from "../../interfaces/signup-request";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-signupdialog',
  templateUrl: './signupdialog.component.html',
  styleUrls: ['./signupdialog.component.css']
})
export class SignupdialogComponent {
  constructor(
    public dialogRef: MatDialogRef<SignupdialogComponent>,
  ) {}

  myGroup: FormGroup = new FormGroup<any>({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    userName: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.min(3)]),
    passwordRetype: new FormControl('', [Validators.required, Validators.min(3)])
  });

  proceed() {
    let req: SignupRequest = {
      userName: this.myGroup.get('userName')?.value,
      password: this.myGroup.get('password')?.value,
      firstName: this.myGroup.get('firstName')?.value,
      lastName: this.myGroup.get('lastName')?.value,
    };
    this.dialogRef.close(req);
  }
}
