package com.springcloud.uaa.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer permissionId;

    @Column(unique = true, nullable = false)
    private String permissionName;

    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;

    // 构造函数
    public Permission() {}

    public Permission(String permissionName, String description) {
        this.permissionName = permissionName;
        this.description = description;
    }

    // Getters 和 Setters
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}