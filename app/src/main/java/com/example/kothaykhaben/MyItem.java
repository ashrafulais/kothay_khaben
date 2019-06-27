package com.example.kothaykhaben;

public class MyItem {

    private String rid, head, desc, imageUrl, resSocialLink;

    //for the foods
    private String foodN;
    private float foodR;

    public MyItem(String foodname, float foodrating) {
        this.foodN = foodname;
        this.foodR = foodrating;
    }

    public MyItem(String resid, String head, String desc, String imageUrl, String reslink) {
        this.rid = resid;
        this.head = head;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.resSocialLink = reslink;
    }

    public String getResId() { return rid; }
    public String getHead() {
        return head;
    }
    public String getDesc() {
        return desc;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getResLink() { return resSocialLink; }
    //public int getResRating() { return resr; }


    public String getFoodName() {
        return foodN;
    }

    public float getFoodRating() {
        return foodR;
    }
}
