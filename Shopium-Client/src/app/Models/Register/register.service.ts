import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root'
})

export class RegisterService {

    private apiServerUrl = environment.apiBaseUrl;

    private registerURL = "Account/Register";

    constructor(private http: HttpClient) { }

    //REGISTER ACCOUNT
    public createNewAccount(username: string, fullName: string, password: string): Observable<HttpResponse<any>> {
        return this.http.post<any>(`${this.apiServerUrl}/${this.registerURL}/`, username);
    }

}