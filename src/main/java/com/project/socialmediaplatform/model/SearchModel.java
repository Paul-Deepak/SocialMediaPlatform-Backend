package com.project.socialmediaplatform.model;

import java.sql.Date;

public class SearchModel {

    private Long userId; 
    private String searchWord; 
    private String userName;
    private String email;
    private Date postDate;

    
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getSearchWord() {
        return searchWord;
    }
    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }
    
    public Date getPostDate() {
        return postDate;
    }
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
    public SearchModel(Long userId, String searchWord, Date postDate, String userName, String email) {
        this.userId = userId;
        this.searchWord = searchWord;
        this.postDate = postDate;
        this.userName = userName;
        this.email = email;
    }
    public SearchModel() {
    } 

    
}
