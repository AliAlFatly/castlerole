import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'app-after-login',
  templateUrl: './after-login.component.html',
  styleUrls: ['./after-login.component.css']
})
export class AfterLoginComponent implements OnInit {

  @ViewChild('canvas', {static: true})
        canvas: ElementRef<HTMLCanvasElement>;

  constructor(
    private authentocationService: AuthenticationService,
    private router: Router,
    private ctx: CanvasRenderingContext2D) { }

  ngOnInit() {
    this.ctx = this.canvas.nativeElement.getContext("2d");
  }

  logout(){
    this.authentocationService.logout();
    this.router.navigate(["login"]);
  }

}
