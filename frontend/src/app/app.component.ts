import { Component } from '@angular/core';
import { ThumbnailComponent } from './modules/movie/components/thumbnail/thumbnail.component';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'movie-cruiser-capsule-frontend';
  token:String
  constructor(private router: Router) { }

  Logout(){
    localStorage.removeItem("jwt_token")
    this.router.navigate(['/login']);
    
  }
}
