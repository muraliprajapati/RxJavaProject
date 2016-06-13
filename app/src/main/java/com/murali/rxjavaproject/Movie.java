
package com.murali.rxjavaproject;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Movie {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("ImdbId")
    @Expose
    private String imdbId;
    @SerializedName("OriginalTitle")
    @Expose
    private String originalTitle;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("TrailerLink")
    @Expose
    private String trailerLink;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("Rating")
    @Expose
    private Double rating;
    @SerializedName("CensorRating")
    @Expose
    private String censorRating;
    @SerializedName("ReleaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("Runtime")
    @Expose
    private Integer runtime;
    @SerializedName("PosterPath")
    @Expose
    private String posterPath;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The imdbId
     */
    public String getImdbId() {
        return imdbId;
    }

    /**
     * 
     * @param imdbId
     *     The ImdbId
     */
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    /**
     * 
     * @return
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 
     * @param originalTitle
     *     The OriginalTitle
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The trailerLink
     */
    public String getTrailerLink() {
        return trailerLink;
    }

    /**
     * 
     * @param trailerLink
     *     The TrailerLink
     */
    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The Country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * 
     * @param region
     *     The Region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * 
     * @return
     *     The genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * 
     * @param genre
     *     The Genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * 
     * @return
     *     The rating
     */
    public Double getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The Rating
     */
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The censorRating
     */
    public String getCensorRating() {
        return censorRating;
    }

    /**
     * 
     * @param censorRating
     *     The CensorRating
     */
    public void setCensorRating(String censorRating) {
        this.censorRating = censorRating;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The ReleaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The runtime
     */
    public Integer getRuntime() {
        return runtime;
    }

    /**
     * 
     * @param runtime
     *     The Runtime
     */
    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    /**
     * 
     * @return
     *     The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * 
     * @param posterPath
     *     The PosterPath
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

}
