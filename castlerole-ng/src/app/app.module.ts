import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/authenticationComponents/login/login.component';
import { LogoutComponent } from './components/authenticationComponents/logout/logout.component';
import { RegisterComponent } from './components/authenticationComponents/register/register.component';
import { TokenInterceptorService } from './services/authentication/token-interceptor.service';
import { GameComponent } from './components/gameComponents/game/game.component';
import { GridComponent } from './components/gameComponents/grid/grid.component';
import { UserInformationComponent } from './components/gameComponents/user-information/user-information.component';
import { NavigatorComponent } from './components/gameComponents/navigator/navigator.component';
import { GameDetailsComponent } from './components/gameComponents/game-details/game-details.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    LogoutComponent,
    RegisterComponent,
    GameComponent,
    GridComponent,
    UserInformationComponent,
    NavigatorComponent,
    GameDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
