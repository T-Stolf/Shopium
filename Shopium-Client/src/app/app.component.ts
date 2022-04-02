import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { ItemService } from './Item/item.service';
import { Item } from './Item/items';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Shopium-Client';
  public items: any[] = [];

  constructor(private itemService: ItemService) { }

  // executes on initialization
  ngOnInit() {
    this.getItems();
  }

  public getItems(): void {
    this.itemService.getItems().subscribe(response => {

      console.log(response);

      const keys = response.headers.keys();
      const mapper = keys.map(key => `${key}:${response.headers.get(key)}`);

      if (response.body != null) {
        for (const data of response.body._embedded.itemList) {
          this.items.push(data);
        }

      }
    });
  }
}
