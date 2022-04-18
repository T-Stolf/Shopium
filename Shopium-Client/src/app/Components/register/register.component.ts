import { Component, OnInit, Input } from '@angular/core';
import { RegisterService } from 'src/app/Models/Register/register.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  @Input() username: string = "";
  @Input() fullName: string = "";
  @Input() password: string = "";
  @Input() address: string = "";
  @Input() role: string = "";


  constructor(private registerService: RegisterService,
    private aroute: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
  }

  public async createNewAccount(): Promise<void> {

    console.log('bing qilin');

    if (this.username == "" || this.fullName == "" || this.password == "") { // if any field is empty do nothing

      return;
    }

    // console.log(this.username);
    // this.registerService.createNewAccount(this.username, this.fullName, this.password).subscribe(response => {
    //   console.log(response);
    // }
    // )


    let apiServerUrl = environment.apiBaseUrl;
    console.log(apiServerUrl);
    let date = new Date().toLocaleTimeString();

    let userData = {"FullName":this.fullName, "password":this.password, "userName":this.username,"DateTimeRegister":date,"Address":this.address,"Role":this.role };
  
    const response = await fetch(`${apiServerUrl}/Account/Register`, {
      method: 'POST', // *GET, POST, PUT, DELETE, etc.
      mode: 'cors',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(userData) // body data type must match "Content-Type" header
    });

    console.log(response);
  
  };



    
}
