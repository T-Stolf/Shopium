import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { ItemService } from './item.service';

import { Item } from './items';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Shopium-Client';
  public items: any[] = []; // this going to hold all the items from the backend

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

      // For Debugging Support, delete when no longer needed - Syed
      console.log(keys);
      console.log(mapper);
      console.log(response.body);

      if (response.body != null) {
        // For Debugging Support, delete when no longer needed - Syed
        console.log(response.body._embedded);

        for (const data of response.body._embedded.itemList) {
          this.items.push(data);
        }

        // For Debugging Support, delete when no longer needed - Syed
        console.log(this.items);
      }

    });

  }

}
