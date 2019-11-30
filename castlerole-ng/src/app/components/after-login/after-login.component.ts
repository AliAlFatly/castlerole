import { Component, ViewChild, ElementRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from "../../services/authentication/authentication.service";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-after-login',
  templateUrl: './after-login.component.html',
  styleUrls: ['./after-login.component.css']
})
export class AfterLoginComponent implements OnInit {

  private canvas: ElementRef;
  private ctx: any;

  constructor(
  http: HttpClient
    ) { }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.ctx = this.canvas.nativeElement.getContext("2d");
  }
}
