import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { ItemService } from '../Models/Item/item.service';
import { Item } from '../Models/Item/items';


@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  title = 'Shopium-Client';
  public items: Item[] = []; // list of items displayed on the catalog

  @Input() searchQuery: string = "bat";


  constructor(private itemService: ItemService) { }

  // executes on initialization
  ngOnInit() {
    this.getAllItems();
  }


  //-------------------------------------------------------------------------------------
  // GET ALL ITEMS
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

  //-------------------------------------------------------------------------------------
  // GET ALL BASED ON SEARCH QUERY
  public getSearchItems(): void {
    this.items = []; // clear out
    if (this.searchQuery == "") { // if search parameter is empty, get all items
      this.getAllItems();
      return;
    }

    console.log(this.searchQuery);
    this.itemService.getSearchItems(this.searchQuery).subscribe(response => {
      console.log(response);
      for (const item of response?.body._embedded.itemList) {
        this.items.push(item);
      }
    });
  }



  //-------------------------------------------------------------------------------------
  // GET ONE BASED
  public getSingularItem(iid: number): Item | undefined {
    for (const item of this.items) {
      if (item.iid == iid) {
        return item;
      }
    }
    return undefined;
  }
}
