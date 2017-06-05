package com.mengyunzhi.waterPurifierApi.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.NotFound;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by panjie on 17/5/12.
 */
@Entity
@ApiModel("用户")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ApiModelProperty("姓名") private String name;

    @ApiModelProperty("上级代理用户")
    @ManyToOne @NotFound @JsonIgnore @JoinColumn(name = "pid")
    private User parentUser;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY) // 防止空json数组
    @OneToMany(mappedBy = "parentUser", fetch = FetchType.EAGER)
    private List<User> childUsers;

    public User() {
    }

    public User(String name, User parentUser, List<User> childUsers) {
        this.name = name;
        this.parentUser = parentUser;
        this.childUsers = childUsers;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", parentUser=" + parentUser +
                ", childUsers=" + childUsers +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getParentUser() {
        return parentUser;
    }

    public void setParentUser(User parentUser) {
        this.parentUser = parentUser;
    }

    public List<User> getChildUsers() {
        return childUsers;
    }

    public void setChildUsers(List<User> childUsers) {
        this.childUsers = childUsers;
    }
}
