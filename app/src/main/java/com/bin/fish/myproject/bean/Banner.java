package com.bin.fish.myproject.bean;

public class Banner {

    /**
     * id : 8
     * title : 2
     * sub_title : 2
     * pic_url : /uploads/admin/admin_thumb/20180810/6876c3b1db9bb735ca81020d02b7a77c.png
     * jump_url : 2
     * type : 1
     */

    private int id;
    private String title;
    private String sub_title;
    private String pic_url;
    private String jump_url;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getJump_url() {
        return jump_url;
    }

    public void setJump_url(String jump_url) {
        this.jump_url = jump_url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
