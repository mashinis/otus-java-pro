package ru.mashinis.db.interaction.ex05_repositories.entity;

import ru.mashinis.db.interaction.ex05_repositories.annotation.Getter;
import ru.mashinis.db.interaction.ex05_repositories.annotation.Setter;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryIdField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryTable;

@RepositoryTable(title = "accounts")
public class Account {
    @RepositoryIdField
    @RepositoryField
    @Getter("getId")
    @Setter("setId")
    private Long id;

    @RepositoryField
    @Getter("getAmount")
    @Setter("setAmount")
    private Long amount;

    @RepositoryField
    @Getter("getAccountType")
    @Setter("setAccountType")
    private String accountType;

    @RepositoryField
    @Getter("getStatus")
    @Setter("setStatus")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account() {
    }

    public Account(Long amount, String accountType, String status) {
        this.amount = amount;
        this.accountType = accountType;
        this.status = status;
    }

    public Account(Long id, Long amount, String accountType, String status) {
        this.id = id;
        this.amount = amount;
        this.accountType = accountType;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                ", accountType='" + accountType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
