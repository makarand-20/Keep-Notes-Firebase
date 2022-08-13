package com.example.notesapptutorial;

public class firebasemodel {

    private String title;
    private String content;
    private String liveDate;

    public firebasemodel()
    {

    }

    public  firebasemodel (String title, String content, String liveDate)
    {
        this.title=title;
        this.content=content;
        this.liveDate=liveDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(String modeldate) {
        this.liveDate = modeldate;
    }
}

