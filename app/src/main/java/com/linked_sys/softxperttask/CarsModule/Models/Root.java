package com.linked_sys.softxperttask.CarsModule.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root<T> {

@SerializedName("status")
@Expose
private Integer status;
@SerializedName("data")
@Expose
private List<T> data = null;

public Integer getStatus() {
return status;
}

public void setStatus(Integer status) {
this.status = status;
}

public List<T> getData() {
return data;
}

public void setData(List<T> data) {
this.data = data;
}

}