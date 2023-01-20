import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-delete-confirm-dialog',
  templateUrl: './delete-confirm-dialog.component.html',
  styleUrls: ['./delete-confirm-dialog.component.css']
})
export class DeleteConfirmDialogComponent {
  message: any;

  constructor(@Inject(MAT_DIALOG_DATA) public data: string | null, public dialogRef: MatDialogRef<DeleteConfirmDialogComponent>) {
    this.message = data;
  }

  confirm(){
    this.dialogRef.close(true)
  }

  cancel(){
    this.dialogRef.close();
  }
}
