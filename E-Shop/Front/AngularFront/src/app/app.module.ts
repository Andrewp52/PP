import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { HomeComponent } from './components/home/home.component';
import { CatalogComponent } from './components/catalog/catalog.component';
import { AboutComponent } from './components/about/about.component';
import { LogindialogComponent } from './components/header/logindialog/logindialog.component';
import { SignupdialogComponent } from './components/header/signupdialog/signupdialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import {FormsModule} from "@angular/forms";
import {ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {HttpClientModule} from "@angular/common/http";
import {httpInterceptorProviders} from './interceptors/http-request-interceptor.service';
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {MatDividerModule} from "@angular/material/divider";
import { CartComponent } from './components/cart/cart.component';
import { ProfileComponent } from './components/authenticated/user/profile/profile.component';
import { OrdersComponent } from './components/authenticated/user/orders/orders.component';
import { MessagesComponent } from './components/authenticated/user/messages/messages.component';
import { UsersComponent } from './components/authenticated/admin/userdata/users/users.component';
import { ProfileAdminComponent } from './components/authenticated/admin/userdata/profile-admin/profile-admin.component';
import { RolesComponent } from './components/authenticated/admin/userdata/roles/roles.component';
import { RoledetailsComponent } from './components/authenticated/admin/userdata/roledetails/roledetails.component';
import { AddressesComponent } from './components/authenticated/admin/userdata/addresses/addresses.component';
import { AddressdetailsComponent } from './components/authenticated/admin/userdata/addressdetails/addressdetails.component';
import { CatalogAdminComponent} from "./components/authenticated/admin/catalog-admin/catalog-admin.component";
import { OrdersAdminComponent } from './components/authenticated/admin/orders-admin/orders-admin.component';
import { MessagesAdminComponent } from './components/authenticated/admin/messages-admin/messages-admin.component';
import { DashBoardComponent } from './components/authenticated/admin/dash-board/dash-board.component';
import { AddressDialogComponent } from './components/authenticated/user/profile/address-dialog/address-dialog.component';
import { PhoneDialogComponent } from './components/authenticated/user/profile/phone-dialog/phone-dialog.component';
import { MainDataDialogComponent } from './components/authenticated/user/profile/main-data-dialog/main-data-dialog.component';
import { ChangePasswordDialogComponent } from './components/authenticated/user/profile/change-password-dialog/change-password-dialog.component';
import { DeleteConfirmDialogComponent } from './components/authenticated/user/profile/delete-confirm-dialog/delete-confirm-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    CatalogComponent,
    AboutComponent,
    LogindialogComponent,
    SignupdialogComponent,
    CartComponent,
    ProfileComponent,
    OrdersComponent,
    MessagesComponent,
    UsersComponent,
    ProfileAdminComponent,
    RolesComponent,
    RoledetailsComponent,
    AddressesComponent,
    AddressdetailsComponent,
    CatalogAdminComponent,
    OrdersAdminComponent,
    MessagesAdminComponent,
    DashBoardComponent,
    AddressDialogComponent,
    PhoneDialogComponent,
    MainDataDialogComponent,
    ChangePasswordDialogComponent,
    DeleteConfirmDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    HttpClientModule,
    MatIconModule,
    MatMenuModule,
    MatDividerModule,

  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
