import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {Sport} from "../model/Sport";

@Injectable({
  providedIn: 'root'
})
export class SportService {


  private URL_API_sport = 'http://localhost:8080/sport/lister';



  private subject1:Subject<Sport[]> = new Subject<Sport[]>();

  constructor(private http:HttpClient) {}

  getSubjectAsObservable(){
    return this.subject1.asObservable()
  }

  getSubject(){
    return this.subject1;
  }

  getListOfFunctionSport(){
    return this.http.get<Sport[]>(this.URL_API_sport);
  }

  GetListSportService():Observable<Array<Sport>> {
    return this.http.get<Array<Sport>>(this.URL_API_sport);
  }


}

