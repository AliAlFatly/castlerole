import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  get form() { return this.loginForm.controls; }

  login() {
    this.authService.login(
      {
        username: this.form.username.value,
        password: this.form.password.value
      }
    )
      .subscribe(success => {
        if (success) {
          this.router.navigate(['']);
        }
      });
  }
  // onSumbit(){
  //   console.log(this.form);
  //   this.loginInfo = new LoginInfoService(
  //     this.form.username,
  //     this.form.password);
  //
  //   this.auth.attemptAuthentication(this.loginInfo).subscribe(
  //     data => {
  //       this.token.saveToken(data.accessToken);
  //       //this.token.saveUsername(data.username);
  //
  //       this.isLoginFailed = false;
  //       this.isLoggedIn = true;
  //       this.reloadPage();
  //     },
  //     error => {
  //       console.log(error);
  //       this.errorMessage = error.message;
  //       this.isLoginFailed = true;
  //     }
  //   );
  // }
  //
  // reloadPage(){
  //   window.location.reload();
  // }
  //
  // checkLogin() {
  //   (this.loginservice.authenticate(this.username, this.password).subscribe(
  //       data => {
  //         this.router.navigate([''])
  //         this.invalidLogin = false
  //       },
  //       error => {
  //         this.invalidLogin = true
  //
  //       }
  //     )
  //   );
  //
  // }

}
