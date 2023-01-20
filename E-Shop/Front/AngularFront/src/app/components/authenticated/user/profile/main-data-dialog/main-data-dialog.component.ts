import {Component, Inject} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {User} from "../../../../../classes/userdata/user";

@Component({
  selector: 'app-main-data-dialog',
  templateUrl: './main-data-dialog.component.html',
  styleUrls: ['./main-data-dialog.component.css']
})
export class MainDataDialogComponent {
  myGroup: FormGroup = new FormGroup<any>({});

  constructor(@Inject(MAT_DIALOG_DATA) public data: User, public dialogRef: MatDialogRef<MainDataDialogComponent>) {
    this.myGroup.addControl('userName', new FormControl(this.data?.userName, Validators.required));
    this.myGroup.addControl('firstName', new FormControl(this.data?.firstName, Validators.required));
    this.myGroup.addControl('lastName', new FormControl(this.data?.lastName, Validators.required));
  }

  proceed(){
      this.dialogRef.close(this.myGroup.value)
  }
}
