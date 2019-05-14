package com.example.a20190422_shilpamahendriker_nycschools.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
// School Class
public class School {

        @SerializedName("borough")
        @Expose
        private String borough;
        @SerializedName("dbn")
        @Expose
        private String dbn;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("school_name")
        @Expose
        private String schoolName;

        public String getBorough() {
            return borough;
        }

        public void setBorough(String borough) {
            this.borough = borough;
        }

        public String getDbn() {
            return dbn;
        }

        public void setDbn(String dbn) {
            this.dbn = dbn;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

}
