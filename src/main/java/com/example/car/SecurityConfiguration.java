package com.example.car;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("blah")
            .password("blah")
            .roles("USER")
                .and()
            .withUser("foo")
            .password("foo")
            .roles("ADMIN");
    }

    protected void configure(HttpSecurity auth) throws Exception{
        auth.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN") // -> path/admin/** hanya akan dapat dikases oleh admin
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN") //path /user/** atau path apapun setelah user hanya dapat diakses oleh role both user or admin
                .antMatchers("/home/**").permitAll() // sama seperti diatas, path /home/** atau path lain yang muncul setelah home/ hanya dapt diakses oleh admin
                .and()
                .formLogin(); // menmpilkan form login ditiap path tersebut
        auth.logout().logoutSuccessUrl("/index");
        // diatas digunakan untuk menentukan url mana yang akan ditampilkan ketika logout berhasil dilakukan
        // normalnya logout akan menampilkan halaman loging kembali yang jika login berhasil
        // akan terdefault ke url :localhost/
    }
    // to make a password has so that the password not exposed to other user
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();

    }
}
