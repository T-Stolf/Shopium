import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ItemService } from '../../Models/Item/item.service';
import { Item } from '../../Models/Item/items';

@Component({
  selector: 'app-catalog-item',
  templateUrl: './catalog-item.component.html',
  styleUrls: ['./catalog-item.component.css']
})
export class CatalogItemComponent implements OnInit {

  public item: any;

  passedID: number = 0;

  constructor(
    private itemservice: ItemService,
    private aroute: ActivatedRoute
  ) { }


  ngOnInit(): void {
    this.aroute.queryParams.subscribe((params: any) => {
      console.log(params);
      this.passedID = params.data;
    });

    this.getItemDetails();
  }

  public getItemDetails(): void {
    this.itemservice.getItem(this.passedID).subscribe(response => {
      console.log(response);
      this.item = response;
    });
  }



}
