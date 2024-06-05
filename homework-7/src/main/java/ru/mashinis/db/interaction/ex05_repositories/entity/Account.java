package ru.mashinis.db.interaction.ex05_repositories.entity;

import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryIdField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryTable;

@RepositoryTable(title = "accounts")
public class Account {
    @RepositoryIdField
    @RepositoryField
    private Long id;

    @RepositoryField
    private Long amount;

    @RepositoryField // TODO (name = "tp");
    private String accountType;

    @RepositoryField
    private String status;

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
