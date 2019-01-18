package com.kubehe.azure.authorization;

import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserAuthorizationService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserAuthorizationService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    UserEntity userEntity = this.userRepository.findByName(name);
    return new User(userEntity.getName(), userEntity.getPassword(),
      AuthorityUtils.createAuthorityList(userEntity.getRoles()));
  }
}
