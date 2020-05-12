package com.linked_sys.softxperttask.CarsModule.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("brand")
@Expose
private String brand;
@SerializedName("constructionYear")
@Expose
private Object constructionYear;
@SerializedName("isUsed")
@Expose
private Boolean isUsed;
@SerializedName("imageUrl")
@Expose
private String imageUrl;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getBrand() {
return brand;
}

public void setBrand(String brand) {
this.brand = brand;
}

public Object getConstructionYear() {
return constructionYear;
}

public void setConstructionYear(Long constructionYear) {
this.constructionYear = constructionYear;
}

public Boolean getIsUsed() {
return isUsed;
}

public void setIsUsed(Boolean isUsed) {
this.isUsed = isUsed;
}

public String getImageUrl() {
return imageUrl;
}

public void setImageUrl(String imageUrl) {
this.imageUrl = imageUrl;
}

}