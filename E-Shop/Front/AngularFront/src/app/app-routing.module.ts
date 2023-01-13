import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {AboutComponent} from "./about/about.component";
import {CatalogComponent} from "./catalog/catalog.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  // { path: 'profile', component: ProfileComponent },
  { path: 'about', component: AboutComponent },
  { path: 'catalog', component: CatalogComponent },
  // { path: 'admin', component: AdminComponent,
  //   children:
  //     [
  //       { path: 'users', component: UsersComponent},
  //       { path: 'users/details/:id', component: UserdetailsComponent },
  //       { path: 'roles', component: RolesComponent},
  //       { path: 'roles/details/:id', component: RoledetailsComponent },
  //       { path: 'addresses', component: AddressesComponent },
  //       { path: 'addresses/details/:id', component: AddressdetailsComponent },
  //       { path: 'dashboard', component: DashboardComponent}
  //     ]
  // }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
