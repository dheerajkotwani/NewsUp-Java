package project.dheeraj.newsup.Model;

import java.util.ArrayList;

import project.dheeraj.newsup.Model.Articles.articles;

public class Feed {

    private String status;
    private String totalResults;
    private ArrayList<project.dheeraj.newsup.Model.Articles.articles> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public ArrayList<project.dheeraj.newsup.Model.Articles.articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<project.dheeraj.newsup.Model.Articles.articles> articles) {
        this.articles = articles;
    }

    public Feed(String status, String totalResults, ArrayList<articles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;

    }

}