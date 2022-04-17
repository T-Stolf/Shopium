import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from 'src/app/Models/Item/item.service';
import { Item } from 'src/app/Models/Item/items';

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {

  public items: Item[] = []; // list of items displayed on the catalog
  public itemID: number = 0;
  public userID: number = 104;
  // Code for specific user interface type should be declared here

  constructor(private itemService: ItemService,
    private aroute: ActivatedRoute,
    private router: Router) { }

  // executes on initialization
  ngOnInit() {
    this.getAllSearchItems();
  }

  //-------------------------------------------------------------------------------------
  // GET ALL ITEMS
  public getAllSearchItems(): void {
    this.items = []; // clear out
    this.itemService.getUserItems(this.userID).subscribe(response => {
      console.log(response);
      //const keys = response.headers.keys();
      //const mapper = keys.map(key => `${key}:${response.headers.get(key)}`);
      for (const data of response?.body._embedded.itemList) {
        this.items.push(data);
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
}
