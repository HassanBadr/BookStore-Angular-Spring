import { Injectable } from '@angular/core';
import { Http, Headers} from '@angular/http';

@Injectable({
  providedIn: 'root'
})
export class GetBookService {

  constructor(private http: Http) { }

  getBook(id: number){
    let url = 'http://localhost:8099/book/' + id;
    let headers = new Headers({
      'Content-type' : 'application/json',
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.get(url, { headers: headers });
  }
}
