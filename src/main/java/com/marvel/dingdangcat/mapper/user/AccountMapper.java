package com.marvel.dingdangcat.mapper.user;

import com.marvel.dingdangcat.domain.user.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marvel on 2019/9/29.
 */
@Repository
public interface AccountMapper {

    Account findById(Long id);

    Account findByUsername(String username);

    Account findByPhone(String phone);

    List<Account> findAll();
}
