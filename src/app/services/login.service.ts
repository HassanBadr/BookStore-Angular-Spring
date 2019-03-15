import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:Http) { }

  sendCredential(username: string, password: string){
    let url = 'http://localhost:8099/token';
    let encodedCredentials = btoa(username + ':' + password);
    let basicHeader = 'Basic ' + encodedCredentials;
    let headers = new Headers({
      'Content-Type' : 'application/x-www-form-urlencoded',
      'Authorization' : basicHeader
    });

    return this.http.get(url, { headers: headers });
  }

  checkSession(){
    let url = 'http://localhost:8099/checkSession';
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, { headers: headers });
  }

  logout(){
    let url = 'http://localhost:8099/user/logout';
    let headers = new Headers({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url,'', { headers: headers });
  }
}
