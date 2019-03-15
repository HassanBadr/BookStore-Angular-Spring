import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  private loggedIn = false;
  constructor(private loginService: LoginService, private router: Router) { }

  logout(){
    this.loginService.logout().subscribe(
      res=>{
        location.reload();
      },
      errorr=>{
         console.log(errorr);
      });
    this.router.navigate(['/']);
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res=>{
        this.loggedIn = true;
      },
      errorr=>{
        this.loggedIn = false;
      });
  }

  toggleDisplay() {
    this.loggedIn = !this.loggedIn;
  }

}
