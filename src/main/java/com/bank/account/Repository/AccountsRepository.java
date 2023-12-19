package com.bank.account.Repository;

import com.bank.account.Entity.Accounts;
import com.bank.account.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts,Long> {
}
