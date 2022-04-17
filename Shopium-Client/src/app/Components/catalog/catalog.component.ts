import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { ItemService } from '../../Models/Item/item.service';
import { Item } from '../../Models/Item/items';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  title = 'Shopium-Client';
  public items: Item[] = []; // list of items displayed on the catalog
  public itemID: number = 0;


  public temp: string = "";

  @Input() searchQuery: string = "";

  constructor(private itemService: ItemService,
    private aroute: ActivatedRoute,
    private router: Router) { }

  // executes on initialization
  ngOnInit() {
    //this.getAllItems();
    this.getMyItems();
    //this.getSearchItems();
  }


  // TEMPORARY
  public getMyItems(): void {
    this.items = [];
    this.itemService.getMyItems().subscribe(response => {
      console.log(response);
      for (const data of response?.body._embedded.itemList) {
        this.items.push(data);
      }
    });
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
  // Navigate to Single Item Page
  public goToCatalogItemPage(id: number) {
    console.log(id);
    this.router.navigate(['/catalog-item'], {
      queryParams: { data: id }
    });
  }

  //-------------------------------------------------------------------------------------
  // GET ONE BASED ON ID
  public getSingularItem(iid: number): Item | undefined {
    for (const item of this.items) {
      if (item.itemID == iid) {
        return item;
      }
    }
    return undefined;
  }
}
