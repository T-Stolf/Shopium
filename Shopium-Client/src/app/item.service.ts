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

    constructor(private http: HttpClient) { }

    // GET ALL ITEMS
    public getItems(): Observable<HttpResponse<any>> {
        return this.http.get<any>(`${this.apiServerUrl}/items`, { observe: 'response' });
    }

    // GET ONE ITEM
    public getItem(iid: number): Observable<Item> {
        return this.http.get<Item>(`${this.apiServerUrl}/items/${iid}`);
    }

    // POST ONE ITEM
    public postItem(item: Item): Observable<Item> {
        return this.http.post<Item>(`${this.apiServerUrl}/items`, item);
    }

    //PUT(update) ONE ITEM
    public putItem(item: Item): Observable<Item> {
        return this.http.put<Item>(`${this.apiServerUrl}/items/${item.iid}`, item);
    }

    // DELETE ONE ITEM
    public deleteItem(iid: number): Observable<void> { //void because we dont return anything, just a status code
        return this.http.delete<void>(`${this.apiServerUrl}/items/${iid}`);
    }

}