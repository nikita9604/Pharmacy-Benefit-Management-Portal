package com.nikita.springbootfinal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private final UserRepository users;

	public CustomUserDetailService(UserRepository users) {
		this.users = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.users.findByUsername(username)
				.map(this::map)
				.orElseThrow(() -> new UsernameNotFoundException("no user"));
	}

	private BridgedUser map(User user) {
		return new BridgedUser(user);
	}


	private static class BridgedUser extends User implements UserDetails {
		Boolean enabled=true;

		public BridgedUser(User user) {
			super(user);


		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> authorities = new HashSet<>();
			String role=this.getRole();

			if ("ROLE_ADMIN".equals(role)) {
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

			}
			else if("ROLE_USER".equals(role))
				authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

			return authorities;
		}

		@Override
		public boolean isAccountNonExpired() {
			return this.enabled;
		}

		@Override
		public boolean isAccountNonLocked() {
			return this.enabled;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return this.enabled;
		}

		@Override
		public boolean isEnabled() {
			return this.enabled;
		}
	}
}
