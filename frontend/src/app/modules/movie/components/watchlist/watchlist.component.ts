import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../movie.service';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movies: Array<Movie>
  useWatchlistApi=true
  constructor(private movieservice:MovieService,private snackBar:MatSnackBar) { 
    this.movies=[]
  }

  ngOnInit() {
    let message='watch List is Empty'
    this.movieservice.getMyWatchList().subscribe((movies) => {
      if(movies.length===0){
        this.snackBar.open(message,'',{
          duration: 1000
        });
      }
      this.movies.push(...movies);
    })
  }

}
