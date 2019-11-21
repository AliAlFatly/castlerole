import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../service/user.service";
import {User} from "../model/user";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  user: User;

  constructor(
    private route: ActivatedRoute,
              private router: Router,
              private userService: UserService
  ) { this.user = new User(); }

  onSubmit() {
    this.userService.authenticate(this.user);
  }

  ngOnInit() {
  }

}
