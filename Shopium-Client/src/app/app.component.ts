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
  public items: Item[] = [];
  public searchQuery: string = "";

  constructor(private itemService: ItemService) { }

  // executes on initialization
  ngOnInit() {
    this.getAllItems();
  }

  /**
   * Get all items from the total items list that relate to the search query
   */
  public getSearchItems(searchQuery: string): void {
    console.log(searchQuery);
    this.items = []; // clear out
    this.itemService.getItems().subscribe(response => {
      console.log(response);
      //const keys = response.headers.keys();
      //const mapper = keys.map(key => `${key}:${response.headers.get(key)}`);
      for (const item of response?.body._embedded.itemList) {
        console.log(item.name.includes(searchQuery));
        console.log(item.description.includes(searchQuery));
        console.log(item.type.includes(searchQuery));

        if (item.name?.includes(this.searchQuery) ||
          item.description?.includes(this.searchQuery) ||
          item.type?.includes(this.searchQuery)) {
          this.items.push(item);
        }
      }
    });
  }

  public getAllItems(): void {
    this.items = []; // clear out
    this.itemService.getItems().subscribe(response => {
      console.log(response);
      //const keys = response.headers.keys();
      //const mapper = keys.map(key => `${key}:${response.headers.get(key)}`);
      for (const data of response?.body._embedded.itemList) {
        this.items.push(data);
      }
    });
  }

  /**
   * depends on getItems()
   */
  public getSingularItem(iid: number): Item | undefined {
    for (const item of this.items) {
      if (item.iid == iid) {
        return item;
      }
    }
    return undefined;
  }
}
