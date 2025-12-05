package Configuration;


import org.springframework.context.annotation.Bean;

@EnableWebSecurity
    @Configuration
    public class SecurityConfig {

        @Bean
        public UserDetailsService userDetailsService() {
            UserDetails superAdmin = User.withDefaultPasswordEncoder()
                    .username("superadmin")
                    .password("password")
                    .roles("SUPER_ADMIN")
                    .build();

            UserDetails customer = User.withDefaultPasswordEncoder()
                    .username("customer1")
                    .password("password")
                    .roles("CUSTOMER")
                    .build();

            return new InMemoryUserDetailsManager(superAdmin, customer);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/process").permitAll() // called by System 1
                            .requestMatchers("/admin/**").hasRole("SUPER_ADMIN")
                            .requestMatchers("/customer/**").hasRole("CUSTOMER")
                            .anyRequest().authenticated()
                    )
                    .httpBasic();
            return http.build();
        }
    }


