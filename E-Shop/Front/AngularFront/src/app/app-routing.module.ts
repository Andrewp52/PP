import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {AboutComponent} from "./components/about/about.component";
import {CatalogComponent} from "./components/catalog/catalog.component";
import {CartComponent} from "./components/cart/cart.component";
import {ProfileComponent} from "./components/authenticated/user/profile/profile.component";

import {OrdersComponent} from "./components/authenticated/user/orders/orders.component";
import {MessagesComponent} from "./components/authenticated/user/messages/messages.component";
import {UsersComponent} from "./components/authenticated/admin/userdata/users/users.component";
import {ProfileAdminComponent} from "./components/authenticated/admin/userdata/profile-admin/profile-admin.component";
import {RolesComponent} from "./components/authenticated/admin/userdata/roles/roles.component";
import {RoledetailsComponent} from "./components/authenticated/admin/userdata/roledetails/roledetails.component";
import {AddressesComponent} from "./components/authenticated/admin/userdata/addresses/addresses.component";
import {AddressdetailsComponent} from "./components/authenticated/admin/userdata/addressdetails/addressdetails.component";
import {OrdersAdminComponent} from "./components/authenticated/admin/orders-admin/orders-admin.component";
import {MessagesAdminComponent} from "./components/authenticated/admin/messages-admin/messages-admin.component";
import {CatalogAdminComponent} from "./components/authenticated/admin/catalog-admin/catalog-admin.component";
import {DashBoardComponent} from "./components/authenticated/admin/dash-board/dash-board.component";
import {ProfileResolver} from "./resolvers/profile.resolver";

const routes: Routes = [
  { path: '', component: HomeComponent },
  // { path: 'profile', component: ProfileComponent },
  { path: 'about', component: AboutComponent },
  { path: 'catalog', component: CatalogComponent },
  { path: 'cart', component: CartComponent },
  { path: 'users', children: [
      {path: 'profile', component: ProfileComponent, resolve: { user: ProfileResolver } },
      {path: 'orders', component: OrdersComponent},
      {path: 'messages', component: MessagesComponent}
    ]
  },
  { path: 'admin',
    children:
      [
        { path: 'dashboard', component: DashBoardComponent},
        { path: 'orders', component: OrdersAdminComponent },
        { path: 'messages', component: MessagesAdminComponent },
        { path: 'catalog', component: CatalogAdminComponent },
        { path: 'users', component: UsersComponent },
        { path: 'users/details/:id', component: ProfileAdminComponent },
        { path: 'roles', component: RolesComponent },
        { path: 'roles/details/:id', component: RoledetailsComponent },
        { path: 'addresses', component: AddressesComponent },
        { path: 'addresses/details/:id', component: AddressdetailsComponent },
        // { path: 'dashboard', component: DashboardComponent}
      ]
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
