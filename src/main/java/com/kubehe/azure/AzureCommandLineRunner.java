package com.kubehe.azure;

import com.kubehe.azure.authorization.UserRole;
import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AzureCommandLineRunner implements CommandLineRunner {

  private UserRepository userRepository;

  @Autowired
  public AzureCommandLineRunner(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    if (this.userRepository.findByName("admin") == null) {
      this.userRepository.save(
        UserEntity.builder()
          .name("admin")
          .password("admin")
          .roles(new String[]{UserRole.ADMIN.getValue()})
          .build());

      SecurityContextHolder.getContext()
        .setAuthentication(
          new UsernamePasswordAuthenticationToken(
            "admin",
            "lolasdfasdfa",
            AuthorityUtils.createAuthorityList(UserRole.ADMIN.getValue())));

      SecurityContextHolder.clearContext();
    }
  }
}
