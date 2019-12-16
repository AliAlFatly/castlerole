import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../../../services/authentication/authentication.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private authService: AuthenticationService, private formBuilder: FormBuilder, private router: Router) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  get form() { return this.loginForm.controls; }

  register() {
    this.authService.register(
      {
        username: this.form.username.value,
        password: this.form.password.value
      }
    )
      .subscribe(success => {
        if (success) {
          this.router.navigate(['/game']);
        }
      });
  }

  toLogin(){
    this.router.navigate(['login']);
  }
  // onSumbit(){
  //   console.log(this.form);
  //   this.signupInfo = new SignupInfoService(
  //     //this.form.name,
  //     this.form.username,
  //     //this.form.email,
  //     this.form.password);
  //   this.auth.signUp(this.signupInfo).subscribe(
  //     data => {
  //       console.log(data);
  //       this.isSignedUp = true;
  //       this.isSignUpFailed = false;
  //     },
  //     error => {
  //       console.log(error);
  //       this.errorMessage = error.message;
  //       this.isSignUpFailed = true;
  //     }
  //   );
  // }
}
