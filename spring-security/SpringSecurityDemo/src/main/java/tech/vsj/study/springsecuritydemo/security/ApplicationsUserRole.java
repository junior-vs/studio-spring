package tech.vsj.study.springsecuritydemo.security;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;

public enum ApplicationsUserRole {

  STUDENT(Sets.newHashSet()), 
  ADMIN(Sets.newHashSet(
      ApplicationUserPermission.STUDENT_READ,
      ApplicationUserPermission.STUDENT_WRITE, 
      ApplicationUserPermission.COURSE_READ,
      ApplicationUserPermission.COURSE_WRITE)), 
  ADMIN_TRAINEE(Sets.newHashSet(
      ApplicationUserPermission.STUDENT_READ,
      ApplicationUserPermission.COURSE_READ));

  private final Set<ApplicationUserPermission> permissions;

  private ApplicationsUserRole(Set<ApplicationUserPermission> permissions) {
    this.permissions = permissions;
  }

  public final Set<ApplicationUserPermission> getPermissions() {
    return permissions;
  }

  public Set<SimpleGrantedAuthority> getAuthorities() {
    Set<SimpleGrantedAuthority> grantedAuthorities = getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
        .collect(Collectors.toSet());

    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return grantedAuthorities;
  }
}
