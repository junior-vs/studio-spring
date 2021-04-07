package tech.vsj.study.springsecuritydemo.security;

import java.util.Set;
import com.google.common.collect.Sets;

public enum ApplicationsUserRole {

  STUDENT(Sets.newHashSet()), ADMIN(Sets.newHashSet(ApplicationUserPermission.STUDENT_READ,
      ApplicationUserPermission.STUDENT_WRITE, ApplicationUserPermission.COURSE_READ,
      ApplicationUserPermission.COURSE_WRITE));


  private final Set<ApplicationUserPermission> permissions;

  private ApplicationsUserRole(Set<ApplicationUserPermission> permissions) {
    this.permissions = permissions;
  }

  public final Set<ApplicationUserPermission> getPermissions() {
    return permissions;
  }
}
