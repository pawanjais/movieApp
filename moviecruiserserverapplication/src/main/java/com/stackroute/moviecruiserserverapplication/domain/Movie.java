package com.stackroute.moviecruiserserverapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="movie",uniqueConstraints= @UniqueConstraint(columnNames = {"movie_id", "user_id"}))
public class Movie {

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int mId;
	
	@Column(name="movie_id")
	private String id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="comments")
	private String comments;
	
	@Column(name="poster_path")
	private String poster_path;
	
	@Column(name="release_date")
	private String release_date;
	
	@Column(name="vote_average")
	private Double vote_average;
	
	@Column(name="vote_count")
	private int vote_count;
	
	@Column(name = "user_id")
	private String userId;



	public Movie(int mId, String id, String title, String comments, String poster_path, String release_date,
			Double vote_average, int vote_count, String userId) {
		super();
		this.mId = mId;
		this.id = id;
		this.title = title;
		this.comments = comments;
		this.poster_path = poster_path;
		this.release_date = release_date;
		this.vote_average = vote_average;
		this.vote_count = vote_count;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Movie [mId=" + mId + ", id=" + id + ", title=" + title + ", comments=" + comments + ", poster_path="
				+ poster_path + ", release_date=" + release_date + ", vote_average=" + vote_average + ", vote_count="
				+ vote_count + ", userId=" + userId + "]";
	}
	
	
	

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public Double getVote_average() {
		return vote_average;
	}

	public void setVote_average(Double vote_average) {
		this.vote_average = vote_average;
	}

	public int getVote_count() {
		return vote_count;
	}

	public void setVote_count(int vote_count) {
		this.vote_count = vote_count;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	
	

}
	
