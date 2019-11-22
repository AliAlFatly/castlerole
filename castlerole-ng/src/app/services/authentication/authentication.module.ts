import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {LoginComponent} from "../../components/login/login.component";
import {AuthenticationService} from "./authentication.service";
import {AuthenticationGuardService} from "../../guards/authentication-guard.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {TokenInterceptorService} from "./token-interceptor.service";
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
//import { MatButtonModule, MatFormFieldModule, MatInputModule } from '@angular/material';


//registers interceptors
@NgModule({
  declarations: [LoginComponent],
  providers: [
    AuthenticationGuardService,
    AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
    ReactiveFormsModule//,
    // MatButtonModule,
    // MatFormFieldModule,
    // MatInputModule
  ]
})
export class AuthenticationModule { }
