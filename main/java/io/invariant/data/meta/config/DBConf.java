package io.invariant.data.meta.config;

import java.util.List;

public class DBConf {

    private String name;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private List<TableConf> tables;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public List<TableConf> getTables() {
        return tables;
    }

    public void setTables(List<TableConf> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return "DBConf{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", tables=" + tables +
                '}';
    }
}
