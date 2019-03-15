import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class GetBookListService {

  constructor(private http: Http) { }

  getBookList(){
    let url = 'http://localhost:8099/book/bookList';
    let headers = new Headers({
      'Content-type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, { headers: headers });
  }
}
