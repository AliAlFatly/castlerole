import { Injectable } from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthenticationService} from '../services/authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})
// guards are classes that implement the CanActive interface
// in approute you set the guard by component call, and it is called
// when called call canActivate function.
export class AuthenticationGuardService implements CanActivate {
  // inject authenticationService to check if user is logged in.
  // inject Router to navigate if true/if false.
  constructor(private authenticationService: AuthenticationService, private router: Router) { }

  canActivate() {
    // if user is logged navigate to /foo
    if (this.authenticationService.isLoggedIn()) {
      this.router.navigate(['/game']);
    }
    // else return true => true means route of login can be activated, if false is returned then route component is empty? or unactivable???
    return !this.authenticationService.isLoggedIn();
  }
}
