import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private http: HttpClient){

  }
urlGetTest : string = "http://localhost:8080/SpringMVCWebSecurityCSRF/api/test";

  checkResponse(url) : Observable<HttpResponse<Object>>{

    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin':'*'
      }),
      withCredentials: true,
      observe: 'response'
     };
    return this.http.get<HttpResponse<Object>>("http://localhost:8080/SpringMVCWebSecurityCSRF/api/test", 
    {observe: 'response', withCredentials: true, headers:{
      'Access-Control-Allow-Origin':'*'
    }}
    )
    .pipe(
      
         tap(resp => {console.log("response " , resp);
          console.log('header', resp.headers.get('Set-Cookie'))})
    );
}

  ngOnInit(): void {
    var appCookie = '';
    this.checkResponse(this.urlGetTest).subscribe(res => {
      console.log('cookie');
      console.log(document.cookie.replace('XSRF-TOKEN=',''));
    });
 
   
    // const httpOptions = {
    //   headers: new HttpHeaders({
    //     'Access-Control-Allow-Origin':'*'
    //   }),
    //   withCredentials: true
    //  };

    // this.http.get("http://localhost:8080/SpringMVCWebSecurityCSRF/api/test", httpOptions).subscribe(Response =>
    // {
    //   const resp = Response;
  
    //   console.log(Response);
    //   console.log(resp);
    // });

    // this.http.post("http://localhost:8080/SpringMVCWebSecurityCSRF/api/postTest",{
    //   headers: new HttpHeaders({
    //     'X-XSRF-TOKEN':document.cookie.replace('XSRF-TOKEN=','')
    //   }),
    //   params: {'X-XSRF-TOKEN':document.cookie.replace('XSRF-TOKEN=','')},
    // withCredentials: true}).subscribe(Response => {
    //   const resp = Response;
    //   console.log(resp);
    // });
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'X-XSRF-TOKEN': document.cookie.replace('XSRF-TOKEN=','') });
  let options = { headers: headers, withCredentials:true };

    this.http.post("http://localhost:8080/SpringMVCWebSecurityCSRF/api/postTest",null,options).subscribe(Response => {
      const resp = Response;
      console.log(resp);
    });
  }
  title = 'testApp';
}
