package ru.mashinis.db.interaction.ex05_repositories.entity;

import ru.mashinis.db.interaction.ex05_repositories.annotation.Getter;
import ru.mashinis.db.interaction.ex05_repositories.annotation.Setter;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryIdField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryTable;

@RepositoryTable(title = "users")
public class User {
    @RepositoryIdField
    @RepositoryField(name = "id")
    @Getter("getId")
    @Setter("setId")
    private Long id;

    @RepositoryField(name = "login")
    @Getter("getLogin")
    @Setter("setLogin")
    private String login;

    @RepositoryField(name = "password")
    @Getter("getPassword")
    @Setter("setPassword")
    private String password;

    @RepositoryField(name = "nickname")
    @Getter("getNickname")
    @Setter("setNickname")
    private String nickname;

    public String getId() {
        return login;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public User() {}

    public User(String login, String password, String nickname) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }

    public User(Long id, String login, String password, String nickname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}