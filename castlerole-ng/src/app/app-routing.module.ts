import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {AuthenticationGuardService} from "./guards/authentication-guard.service";
import {RegisterComponent} from "./components/register/register.component";

import {GameComponent} from "./components/game/game.component";


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '/login' },
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [AuthenticationGuardService]
  },
  {
    path: 'register',
    component: RegisterComponent,
    canActivate: [AuthenticationGuardService]
  },
  {
    path: 'game',
    component: GameComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
