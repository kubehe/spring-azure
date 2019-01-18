package com.kubehe.azure;

import com.kubehe.azure.domain.UserEntity;
import com.kubehe.azure.repository.UserRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AzureCommandLineRunner implements CommandLineRunner {

  private UserRepository userRepository;

  @Autowired
  public AzureCommandLineRunner(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    this.userRepository.save(UserEntity.builder().name("admin").password("admin").roles(new String[]{"ADMIN"}).build());

    SecurityContextHolder.getContext().setAuthentication(
      new UsernamePasswordAuthenticationToken("admin", "lolasdfasdfa",
        AuthorityUtils.createAuthorityList("ADMIN")));

    SecurityContextHolder.clearContext();
  }
}
