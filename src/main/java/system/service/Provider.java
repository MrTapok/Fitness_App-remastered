package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import system.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service("provider")
public class Provider implements AuthenticationProvider
{

    private UserService userService;

    @Autowired
    public void setUserServiceService(UserService userService)
    {
        this.userService = userService;
    }


    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String login = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        String password = userService.toMD5(pwd);
        User user = null;
        for (Object user1 : userService.getAllUsers()){
            if (((User) user1).getName().equals(login) && ((User) user1).getPassword().equals(password))
            {
                user = (User) user1;
            }
        }
        if (user!=null)
        {
            List<GrantedAuthority> grantedAuth = new ArrayList<GrantedAuthority>();
            grantedAuth.add(new SimpleGrantedAuthority(user.getRole()));
            return new UsernamePasswordAuthenticationToken(login, password, grantedAuth);
        }
        else {
            return null;
        }
    }

public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }
}
