package com.marineindustryproj.service.parseExcel;

public class KeyValueDTO{
    public KeyValueDTO(){}
    public KeyValueDTO(Long id,String title)
    {
        this.setId(id);
        this.setTitle(title);
    }

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
