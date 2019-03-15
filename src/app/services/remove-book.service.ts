import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Book } from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class RemoveBookService {

  constructor(private http: Http) { }

  sendBook(bookId: number){
    let url = 'http://localhost:8099/book/remove';
    let headers = new Headers({
      'Content-type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, bookId, { headers: headers });
  }
}
