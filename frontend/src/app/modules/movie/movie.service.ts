import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Movie } from './movie';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MovieService {
  apiKey:string;
  tmbdEndpoint: string
  imagePrefix: string
  springEndPoint:string
  search:string
  //watchListEndPoint: string

  constructor(private http:HttpClient) { 
    this.apiKey="api_key=ce4c8e68409b66e2313e5884d4b6ef00"
    this.tmbdEndpoint="https://api.themoviedb.org/3/movie"
    this.imagePrefix='https://image.tmdb.org/t/p/w500';
    this.springEndPoint='http://localhost:1112/api/movieservice/movie'
    this.search="https://api.themoviedb.org/3/search/movie?"
    //this.watchListEndPoint='http://localhost:3000/watchlist'
  }

  getMovies(type: string, page: number =1): Observable<Array<Movie>>{
    console.log(type);
   const endPoint= `${this.tmbdEndpoint}/${type}?${this.apiKey}&language=en-US&page=${page}`
   return this.http.get(endPoint).pipe(
     map(this.pickMovieResults),
     map(this.trasformPosterPath.bind(this))
   )
  }

  searchMovies(searchkey:string,page: number = 1): Observable <Array<Movie>>{
    if(searchkey.length>0){
      const url=`${this.search}${this.apiKey}&language=en-US&page=${page}&include_adult=false&query=${searchkey}`;
      return this.http.get(url).pipe(
        
        map(this.pickMovieResults),
     map(this.trasformPosterPath.bind(this))
      );
    }
  }

  saveMyWatchListMovies(movie){
    console.log(JSON.stringify(movie))
    return this.http.post(this.springEndPoint,movie);
  }

  getMyWatchList(): Observable <Array<Movie>>{
    return this.http.get<Array<Movie>>(this.springEndPoint);
  }

  deleteFromMyWatchList(movie:Movie){
    console.log(movie)
    const url = `${this.springEndPoint}/${movie.id}/${movie.userId}`
    return this.http.delete(url,{responseType:'text'})
  }

  updateWatchlist(movie) {
    const url= `${this.springEndPoint}/${movie.id}`;
    return this.http.put(url, movie);
  }


  trasformPosterPath(movies): Array<Movie>{
    return movies.map(movie => {
      movie.poster_path = `${this.imagePrefix}${movie.poster_path}`;
      console.log(movie.poster_path);
      return movie;
    });
  }

  pickMovieResults(response){
    return response['results'];
  }

  /* addMovieToWatchList(movie){
    return this.http.post(this.watchListEndPoint,movie);
  }

  getWatchListedMovie(): Observable<Array<Movie>> {
    return this.http.get<Array<Movie>>(this.watchListEndPoint);
  } */






}
