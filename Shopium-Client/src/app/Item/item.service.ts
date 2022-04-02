import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Item } from './items';

@Injectable({
    providedIn: 'root'
})

// This class contains functions that allow us to communicate with the backend
export class ItemService {
    private apiServerUrl = environment.apiBaseUrl;
    private entityUrl = "items";

    constructor(private http: HttpClient) { }

    // GET ALL 
    public getItems(): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.apiServerUrl}/${this.entityUrl}`, { observe: 'response' });
    }

    // GET ONE 
    public getItem(iid: number): Observable<HttpResponse<any>> {
        return this.http.get<HttpResponse<any>>(`${this.apiServerUrl}/${this.entityUrl}/${iid}`);
    }

    // POST ONE 
    public postItem(item: Item): Observable<HttpResponse<any>> {
        return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/${this.entityUrl}`, item);
    }

    //PUT(update) ONE 
    public putItem(item: Item): Observable<HttpResponse<any>> {
        return this.http.put<HttpResponse<any>>(`${this.apiServerUrl}/${this.entityUrl}/${item.iid}`, item);
    }

    // DELETE ONE 
    public deleteItem(iid: number): Observable<void> { //void because we dont return anything, just a status code
        return this.http.delete<void>(`${this.apiServerUrl}/${this.entityUrl}/${iid}`);
    }

}