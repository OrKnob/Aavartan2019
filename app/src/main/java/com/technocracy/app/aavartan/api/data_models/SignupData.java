package com.technocracy.app.aavartan.api.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SignupData implements Serializable {

    public SignupData(String password, String name, String email, String contact, String college, String branch, String course, int sem, String city) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.college = college;
        this.branch = branch;
        this.course = course;
        this.sem = sem;
        this.city = city;
    }

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("college")
    @Expose
    private String college;

    @SerializedName("branch")
    @Expose
    private String branch;

    @SerializedName("course")
    @Expose
    private String course;

    @SerializedName("sem")
    @Expose
    private int sem;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("key")
    @Expose
    private String key;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /*public JSONObject toJSONList(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("password", getPassword());
            jsonObject.put("name",getName());
            jsonObject.put("email", getEmail());
            jsonObject.put("contact", getContact());
            jsonObject.put("college", getCollege());
            jsonObject.put("branch", getBranch());
            jsonObject.put("course", getCourse());
            jsonObject.put("sem", getSem());
            jsonObject.put("city", getCity());

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
        List<JSONObject> jsonObjectList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("password", getPassword());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("password");

            jsonObject.put("name", getName());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("name");

            jsonObject.put("email", getEmail());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("email");

            jsonObject.put("contact", getContact());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("contact");

            jsonObject.put("college", getCollege());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("college");

            jsonObject.put("branch", getBranch());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("branch");

            jsonObject.put("course", getCourse());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("course");

            jsonObject.put("sem", getSem());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("sem");

            jsonObject.put("city", getCity());
            jsonObjectList.add(jsonObject);
            jsonObject.remove("city");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjectList;

    }*/

    public String toJSONString(){

        return "{\"password\" : \"" + getPassword() + "\",\"name\" : \"" + getName()
                + "\",\"email\" : \"" + getEmail() + "\",\"contact\" : \"" + getContact()
                + "\",\"college\" : \"" + getCollege() + "\",\"branch\" : \"" + getBranch()
                + "\",\"course\" : \"" + getCourse() + "\",\"sem\" : \"" + getSem()
                + "\",\"city\" : \"" + getCity() + "\"}";
    }

}
