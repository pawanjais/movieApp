import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  @Input()
  movies: Array<Movie>
  @Input()
  useWatchlistApi: boolean

  constructor(private movieservice:MovieService,private snackBar:MatSnackBar) { 
    this.movies = [];
    
  }

  ngOnInit() {
  }

  addMovieToWatchList(movie){
    console.log("Movie object",movie)
    let message=`${movie.title} add to watchList`
    console.log("Movie title",movie.title)
    this.movieservice.saveMyWatchListMovies(movie).subscribe(() =>{
      this.snackBar.open(message,'' ,{
        duration: 1000
      })
    });
  }

  deleteFromWatchList(movie){
    let message = `${movie.title} deleted from your watchList`
    for(var i=0;i< this.movies.length;i++){
      if(this.movies[i].title === movie.title){
        this.movies.splice(i,1);
      }
    }
    this.movieservice.deleteFromMyWatchList(movie).subscribe((movies) =>{
      this.snackBar.open(message,'',{
        duration: 1000
      })
    })
  }

}
