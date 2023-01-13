import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {LoginRequest} from "../../interfaces/login-request";


@Component({
  selector: 'app-logindialog',
  templateUrl: './logindialog.component.html',
  styleUrls: ['./logindialog.component.css']
})


export class LogindialogComponent {

  constructor(
    public dialogRef: MatDialogRef<LogindialogComponent>,
  ) {}

  myGroup = new FormGroup<any>({
    userName: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  proceed() {
    this.dialogRef.close(this.myGroup.value);
  }
}
