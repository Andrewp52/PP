import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {LogindialogComponent} from "./logindialog/logindialog.component";
import {SignupdialogComponent} from "./signupdialog/signupdialog.component";
import {AuthService} from "../../services/backend/auth.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{
  constructor(public dialog: MatDialog, private authService: AuthService, private router: Router) {}

  isAdmin: boolean = false;
  adminPanelActive: boolean = false;
  isLoggedIn: boolean = false;
  loginError: any
  searchGroup: FormGroup = new FormGroup<any>({
    keyword: new FormControl('')
  });

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    if(this.isLoggedIn){
      this.loginError = undefined;
      this.isAdmin = this.authService.hasRole('ADMIN');
    } else {
      this.isAdmin = false;
      if(this.loginError != undefined){
        this.loginDialogOpen();
      }
    }

  }

  loginDialogOpen() {
    this.dialog.open(LogindialogComponent, {
      width: '250px',
      height: '250px',
      data: {error: this.loginError}
    }).afterClosed().subscribe( val => {
      if (val) {
        this.authService.login(val).subscribe({
          next: val => {
            this.loginError = undefined;
            this.ngOnInit();
          }, error: err => {
            this.loginError = err;
            this.ngOnInit();
          }
        });
      }
    });
  }

  signupDialogOpen() {
    this.dialog.open(SignupdialogComponent, {
      width: '300px',
      height: '480px'
    }).afterClosed().subscribe( val => {
        if(val){
          this.authService.signup(val).subscribe({
            next: value => {

            }, error: err => {

            }
          })
        }
    });
  }

  logout() {
    this.authService.logoff().subscribe(r => location.reload());
  }

  toggleAdminPanel() {
    this.adminPanelActive = !this.adminPanelActive;
  }

  search() {
    console.log(this.searchGroup.get('keyword')?.value)
  }
}
