import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule ,HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule }    from '@angular/forms';
import { MovieService } from './movie.service';
import { ContainerComponent } from './components/container/container.component';
import { MovieRoutingModule } from './movie-routing.module';

import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptor } from './interceptor.service';

@NgModule({
  declarations: [ThumbnailComponent, ContainerComponent, WatchlistComponent, TmdbContainerComponent, MovieDialogComponent, SearchComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    HttpClientModule
    
  ],
  exports:[
    MovieRoutingModule,
    ThumbnailComponent,
    WatchlistComponent,
    MovieDialogComponent
  ],
  providers: [
    MovieService,{
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi:true
    }
  ],
  entryComponents:[
    MovieDialogComponent
  ]
})
export class MovieModule { }
