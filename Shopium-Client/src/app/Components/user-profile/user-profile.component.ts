import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ItemService } from 'src/app/Models/Item/item.service';
import { Item } from 'src/app/Models/Item/items';


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public items: Item[] = [];
  public userID: number = 104;

  constructor(private itemService: ItemService,
    private aroute: ActivatedRoute,
    private router: Router) { }

  // executes on initialization
  ngOnInit() {
    this.getAllUserItems();
  }

  //-------------------------------------------------------------------------------------
  // GET ALL ITEMS
  public getAllUserItems(): void {
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
