package com.example.a7invensun.verifydemo.RxJavaDemo.model;

import java.util.List;

public class StudentModel {
    private String name;
    private List<Courses> courses;

    public static class Courses{
        private String name;

        public Courses(String name) {
            this.name = name;
        }



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public StudentModel(String name, List<Courses> courses) {
        this.name = name;
        this.courses = courses;
    }


}
