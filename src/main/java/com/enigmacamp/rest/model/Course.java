package com.enigmacamp.rest.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_courses")
public class Course {

    @Id
    @GeneratedValue(generator = "system-uuid")//seharusnya ini bagian yang menentukan jenis apa yang akan di assign ke db, dalam kasus ini yang dipilih adalah uuid, dan ini bukan di manage by jpa
    //kemungkinan variable generator diatas dimaksudkan bahwa generator mereferensikan ke anotasi generator dibawah
    @GenericGenerator(name = "system-uuid" ,strategy = "uuid2")//name adalah nama yang direferensikan, strategy disinilah yang mksdnya menentukan nilai yang di assign, dan generate disini di manage oleh java
    @Column(name = "course_id", nullable = false)
    private String id;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    private String title;

    @Column(name = "description", nullable = false, length = 250)
    private String description;

    @Column(name = "link", nullable = false, length = 200)
    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    ////////

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "type_id")
//    private CourseType courseType;
//
//    public CourseType getCourseType() {
//        return courseType;
//    }
//
//    public void setCourseType(CourseType courseType) {
//        this.courseType = courseType;
//    }
//
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "info_id")
//    private CourseInfo courseInfo;
//
//    public CourseInfo getCourseInfo() {
//        return courseInfo;
//    }
//
//    public void setCourseInfo(CourseInfo courseInfo) {
//        this.courseInfo = courseInfo;
//    }
}
