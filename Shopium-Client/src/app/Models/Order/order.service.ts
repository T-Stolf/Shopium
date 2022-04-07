import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiServerUrl = environment.apiBaseUrl;

  private entityUrl = "orders";

  constructor(private http: HttpClient) { }

  //GET ALL
  public getOrders(): Observable<HttpResponse<any>> {
    return this.http.get<any>(`${this.apiServerUrl}/${this.entityUrl}`, { observe: 'response' });
  }

  // GET ONE 
  public getOrder(id: number): Observable<HttpResponse<any>> {
    return this.http.get<HttpResponse<any>>(`${this.apiServerUrl}/${this.entityUrl}/${id}`);
  }

  // POST ONE 
  public postOrder(order: Order): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/${this.entityUrl}`, order);
  }

  //PUT(update) ONE 
  public putOrder(order: Order): Observable<HttpResponse<any>> {
    return this.http.put<HttpResponse<any>>(`${this.apiServerUrl}/${this.entityUrl}/${order.orderID}`, order);
  }

  // DELETE ONE 
  public deleteOrder(id: number): Observable<void> { //void because we dont return anything, just a status code
    return this.http.delete<void>(`${this.apiServerUrl}/${this.entityUrl}/${id}`);
  }


}
