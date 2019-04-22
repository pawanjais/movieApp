import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { HttpClient } from '@angular/common/http';
import { MovieService } from '../../movie.service';
import { MatSnackBar } from '@angular/material/snack-bar'
import { MatDialog } from '@angular/material/dialog'
import { Output,EventEmitter } from '@angular/core';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})
export class ThumbnailComponent implements OnInit {
  @Input()
  movie:Movie

  @Input()
  useWatchlistApi:boolean

  @Output()
  addMovie=new EventEmitter()

  @Output()
  deleteMovie=new EventEmitter()


  constructor(private snackBar:MatSnackBar,private dialog:MatDialog) {}

  ngOnInit() { 
  }

  addToWatchList(){
    this.addMovie.emit(this.movie); 
  }

  deleteFromWatchList(){
    console.log('Moviedeleted', this.movie.id)
    this.deleteMovie.emit(this.movie)
  }

  updateFromWatchList(actionType){
    console.log('Movie is getting updated')
    let dialogRef = this.dialog.open(MovieDialogComponent,{
      width: '400px',
      data: { obj: this.movie, actionType:actionType }
    })
    console.log('open dialog')
    dialogRef.afterClosed().subscribe(result => {
      console.log("The Dialog was closed")
    })
  }

}
