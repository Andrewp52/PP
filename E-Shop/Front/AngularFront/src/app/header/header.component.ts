import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {LogindialogComponent} from "./logindialog/logindialog.component";
import {SignupdialogComponent} from "./signupdialog/signupdialog.component";
import {AuthService} from "../services/backend/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  constructor(public dialog: MatDialog, private authService: AuthService) {}

  isAdmin: boolean = false;
  isLoggedIn: boolean = false;

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.isAdmin = this.authService.hasRole('ADMIN');
  }

  loginDialogOpen() {
    this.dialog.open(LogindialogComponent, {
      width: '250px',
      height: '250px'
    }).afterClosed().subscribe({
      next: val => {
        this.authService.login(val).subscribe({
            next: value => {
              console.log(value);
            }
          }
        )
      }
    });
  }

  signupDialogOpen() {
    this.dialog.open(SignupdialogComponent, {
      width: '300px',
      height: '480px'
    }).afterClosed().subscribe({
      next: value => {
        console.log(value)
      }
    });
  }

  logoff() {
    this.authService.logoff().subscribe({
      next: value => {
        this.isLoggedIn = this.authService.isLoggedIn();
      }
    });
  }
}
